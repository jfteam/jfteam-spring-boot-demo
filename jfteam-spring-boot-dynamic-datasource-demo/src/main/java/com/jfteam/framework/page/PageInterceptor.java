package com.jfteam.framework.page;import org.apache.ibatis.executor.Executor;import org.apache.ibatis.executor.statement.StatementHandler;import org.apache.ibatis.mapping.BoundSql;import org.apache.ibatis.mapping.MappedStatement;import org.apache.ibatis.plugin.*;import org.apache.ibatis.session.Configuration;import org.apache.ibatis.session.ResultHandler;import org.apache.ibatis.session.RowBounds;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import org.springframework.util.StringUtils;import java.sql.Connection;import java.sql.SQLException;import java.sql.Statement;import java.util.ArrayList;import java.util.List;import java.util.Map;import java.util.Properties;/** * Created with IntelliJ IDEA. * Description: mybatis分页拦截器 * User: fengwenping * Date: 2017-10-29 * Time: 下午7:13 */@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})public class PageInterceptor implements Interceptor {    private static final Logger LOGGER = LoggerFactory.getLogger(PageInterceptor.class);    private static final int RESULT_MODE_ALL = 0;    private static final int RESULT_MODE_TOTAL_ROW = 1;    private static final int RESULT_MODE_DATA_SET = 2;    private static final String COUNT_SQL_POST_FIX = "Count";    @Override    public Object intercept(Invocation invocation) throws Throwable {        Object[] queryArgs = invocation.getArgs();        MappedStatement mappedStatement = (MappedStatement) queryArgs[0];        String sqlId = mappedStatement.getId();        Object parameter = queryArgs[1];        PageParam pageParam = checkInvocation(sqlId, parameter);        if (null == pageParam) {            return invocation.proceed();        }        Executor executor = (Executor) invocation.getTarget();        if (((pageParam.getResultMode() == RESULT_MODE_ALL) && pageParam.getTotalRows() == 0) || pageParam.getResultMode() == RESULT_MODE_TOTAL_ROW) {            queryCount(queryArgs, mappedStatement, sqlId, pageParam, executor);        }        List<Object> resultList = new ArrayList<>();        if (((pageParam.getResultMode() == RESULT_MODE_ALL) && pageParam.getTotalRows() > 0) || pageParam.getResultMode() == RESULT_MODE_DATA_SET) {            resultList = queryResultList(executor, mappedStatement, queryArgs);            if (resultList.size() > pageParam.getTotalRows()) {                pageParam.setTotalRows(resultList.size());            }        }        PageBean<Object> pagedResult = new PageBean<>();        pagedResult.setPageParam(pageParam);        pagedResult.setResult(resultList);        List<PageBean> list = new ArrayList<>();        list.add(pagedResult);        return list;    }    private List<Object> queryResultList(Executor executor, MappedStatement mappedStatement, Object[] queryArgs) throws SQLException {        Object parameterObject = queryArgs[1];        RowBounds rowBounds = (RowBounds) queryArgs[2];        ResultHandler resultHandler = (ResultHandler) queryArgs[3];        Configuration configuration = mappedStatement.getConfiguration();        BoundSql boundSql = mappedStatement.getBoundSql(parameterObject);        StatementHandler statementHandler = configuration.newStatementHandler(executor, mappedStatement, parameterObject, rowBounds, resultHandler, boundSql);        Statement statement = null;        try {            statement = preparedStatement(executor, statementHandler);            return statementHandler.query(statement, resultHandler);        } finally {            closeStatement(statement);        }    }    private Statement preparedStatement(Executor executor, StatementHandler statementHandler) throws SQLException {        Statement statement = null;        Connection connection = executor.getTransaction().getConnection();        try {            statement = statementHandler.prepare(connection, null);            statementHandler.parameterize(statement);            return statement;        } catch (SQLException ex) {            closeStatement(statement);            LOGGER.error("PageInterceptor preparedStatement error.", ex);            throw ex;        } catch (Exception e) {            LOGGER.error("PageInterceptor preparedStatement error.", e);        }        return statement;    }    private void closeStatement(Statement statement) {        if (statement != null) {            try {                statement.close();            } catch (SQLException ex) {                LOGGER.error("PageInterceptor closeStatement error.", ex);            }        }    }    private void queryCount(Object[] queryArgs, MappedStatement mappedStatement, String sqlId, PageParam pageParam, Executor executor) throws SQLException {        String queryCountSqlId = sqlId + COUNT_SQL_POST_FIX;        Configuration configuration = mappedStatement.getConfiguration();        MappedStatement queryCountMS = configuration.getMappedStatement(queryCountSqlId);        MappedStatement newMappedStatement = buildMappedStatement(queryCountMS);        List<Object> queryCountList = queryResultList(executor, newMappedStatement, queryArgs);        int totalRows = Integer.parseInt(queryCountList.get(0).toString());        pageParam.setTotalRows(totalRows);    }    private MappedStatement buildMappedStatement(MappedStatement mappedStatement) {        MappedStatement.Builder builder = new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(), mappedStatement.getSqlSource(), mappedStatement.getSqlCommandType());        builder.resource(mappedStatement.getResource());        builder.parameterMap(mappedStatement.getParameterMap());        builder.fetchSize(mappedStatement.getFetchSize());        builder.timeout(mappedStatement.getTimeout());        builder.statementType(mappedStatement.getStatementType());        builder.resultSetType(mappedStatement.getResultSetType());        builder.cache(mappedStatement.getCache());        builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());        builder.useCache(mappedStatement.isUseCache());        builder.keyGenerator(mappedStatement.getKeyGenerator());        builder.keyProperty(StringUtils.arrayToCommaDelimitedString(mappedStatement.getKeyProperties()));        builder.resultMaps(mappedStatement.getResultMaps());        return builder.build();    }    private PageParam checkInvocation(String sqlId, Object parameter) {        if (null == parameter) {            return null;        }        if (null != sqlId && !sqlId.endsWith(COUNT_SQL_POST_FIX)) {            return findPageParam(parameter);        }        return null;    }    @SuppressWarnings("unchecked")    private PageParam findPageParam(Object parameter) {        if (null == parameter) {            return null;        }        if (parameter instanceof Map) {            Map<String, Object> parameterMap = (Map<String, Object>) parameter;            for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {                if (entry.getValue() instanceof PageParam) {                    return (PageParam) entry.getValue();                }            }        }        if (parameter instanceof PageParam) {            return (PageParam) parameter;        }        return null;    }    @Override    public Object plugin(Object target) {        return Plugin.wrap(target, this);    }    @Override    public void setProperties(Properties properties) {    }}