package com.jfteam.framework.page;import com.fasterxml.jackson.annotation.JsonIgnoreProperties;import org.slf4j.Logger;import org.slf4j.LoggerFactory;import java.util.ArrayList;import java.util.List;/** * Created with IntelliJ IDEA. * Description: 分页实体类 * User: fengwenping * Date: 2017-10-29 * Time: 下午7:13 */@JsonIgnoreProperties(ignoreUnknown = true, value = {"filters"})public class PageParam {    private static final Logger LOGGER = LoggerFactory.getLogger(PageParam.class);    public static final int DEFAULT_PAGE_SIZE = 20;    public static final int MAX_PAGE_SIZE = 500;    private int totalRows;    private int curPage = 1;    private int pageSize;    /**     * 0或不设置显示结果集和总记录数,1显示总记录数,2显示结果集     */    private int resultMode;    private int startIndex;    private int endIndex;    private String orderBy;    private String filterStr;    private List<FilterParam> filters = new ArrayList<>();    public PageParam() {        this.pageSize = DEFAULT_PAGE_SIZE;    }    public int getTotalRows() {        return totalRows;    }    public void setTotalRows(int totalRows) {        this.totalRows = totalRows;    }    public int getCurPage() {        return curPage;    }    public void setCurPage(int curPage) {        this.curPage = curPage;    }    public int getPageSize() {        return pageSize;    }    public void setPageSize(int pageSize) {        if ((pageSize <= 0) || (pageSize > MAX_PAGE_SIZE)) {            LOGGER.error("The pageSize param is too large, default page size will be used. You can modify the MaxPageSize use the following method");            return;        }        this.pageSize = pageSize;    }    public int getResultMode() {        return resultMode;    }    public void setResultMode(int resultMode) {        this.resultMode = resultMode;    }    public int getStartIndex() {        return (this.startIndex == 0) ? (this.curPage - 1) * this.pageSize + 1 : this.startIndex;    }    public void setStartIndex(int startIndex) {        this.startIndex = startIndex;    }    public int getEndIndex() {        if ((this.endIndex - this.startIndex) > MAX_PAGE_SIZE) {            LOGGER.error("The endIndex param is too large, default page size will be used. You can modify the MaxPageSize use the following method");            return this.startIndex + DEFAULT_PAGE_SIZE;        }        return (this.endIndex - 1 > this.totalRows) ? this.totalRows : (this.endIndex == 0) ? this.curPage * this.pageSize : this.endIndex;    }    public void setEndIndex(int endIndex) {        this.endIndex = endIndex;    }    public String getOrderBy() {        return orderBy;    }    public void setOrderBy(String orderBy) {        this.orderBy = orderBy;    }    public String getFilterStr() {        return filterStr;    }    public void setFilterStr(String filterStr) {        this.filterStr = filterStr;    }    public List<FilterParam> getFilters() {        return filters;    }    public void setFilters(List<FilterParam> filters) {        this.filters = filters;    }}