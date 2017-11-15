package com.jfteam.framework.datasource;

/**
 * Created with IntelliJ IDEA.
 * Description: 数据源持有者,切换工具
 * User: fengwenping
 * Date: 2017-11-15
 * Time: 下午9:17
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 动态设置数据源
     *
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType) {
        contextHolder.set(dataSourceType);
    }

    /**
     * 获取动态设置的数据源
     *
     * @return
     */
    public static String getDataSourceType() {
        return contextHolder.get();
    }

    /**
     * 移除数据源,恢复默认数据源
     */
    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
