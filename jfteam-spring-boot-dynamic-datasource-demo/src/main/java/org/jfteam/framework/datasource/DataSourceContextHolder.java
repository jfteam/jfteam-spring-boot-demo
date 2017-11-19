package org.jfteam.framework.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * Description: 数据源持有者,切换工具
 * User: fengwenping
 * Date: 2017-11-15
 * Time: 下午9:17
 */
public class DataSourceContextHolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceContextHolder.class);

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 动态设置数据源
     *
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("设置当前数据源为: {}", dataSourceType);
        }
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取动态设置的数据源
     *
     * @return
     */
    public static String getDataSourceType() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("系统当前数据源为: {}", contextHolder.get());
        }
        return contextHolder.get();
    }

    /**
     * 移除数据源,恢复默认数据源
     */
    public static void clearDataSourceType() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("恢复当前数据源为默认数据源.");
        }
        contextHolder.remove();
    }
}
