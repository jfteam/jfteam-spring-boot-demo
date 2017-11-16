package com.jfteam.framework.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created with IntelliJ IDEA.
 * Description: 定义数据库bean属性
 * User: fengwenping
 * Date: 2017-11-15
 * Time: 下午10:32
 */
@ConfigurationProperties(prefix = "spring.datasource.dynamic")
public class DynamicDataSourceProperties {

    private JdbcProperties master;

    private JdbcProperties slave;

    public JdbcProperties getMaster() {
        return master;
    }

    public void setMaster(JdbcProperties master) {
        this.master = master;
    }

    public JdbcProperties getSlave() {
        return slave;
    }

    public void setSlave(JdbcProperties slave) {
        this.slave = slave;
    }

    public static class JdbcProperties {
        private String driverClassName = "com.mysql.jdbc.Driver";

        private String url = "jdbc:mysql://127.0.0.1:3306/test";

        private String username = "root";

        private String password = "root";

        private String connectionProperties = "";

        private long maxWaitMillis = 1800;

        private int initialSize = 5;

        private int maxTotal = 8;

        private int maxIdle = 5;

        private int minIdle = 2;

        private String validationQuery = "SELECT 1";

        public String getDriverClassName() {
            return driverClassName;
        }

        public void setDriverClassName(String driverClassName) {
            this.driverClassName = driverClassName;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getConnectionProperties() {
            return connectionProperties;
        }

        public void setConnectionProperties(String connectionProperties) {
            this.connectionProperties = connectionProperties;
        }

        public long getMaxWaitMillis() {
            return maxWaitMillis;
        }

        public void setMaxWaitMillis(long maxWaitMillis) {
            this.maxWaitMillis = maxWaitMillis;
        }

        public int getInitialSize() {
            return initialSize;
        }

        public void setInitialSize(int initialSize) {
            this.initialSize = initialSize;
        }

        public int getMaxTotal() {
            return maxTotal;
        }

        public void setMaxTotal(int maxTotal) {
            this.maxTotal = maxTotal;
        }

        public int getMaxIdle() {
            return maxIdle;
        }

        public void setMaxIdle(int maxIdle) {
            this.maxIdle = maxIdle;
        }

        public int getMinIdle() {
            return minIdle;
        }

        public void setMinIdle(int minIdle) {
            this.minIdle = minIdle;
        }

        public String getValidationQuery() {
            return validationQuery;
        }

        public void setValidationQuery(String validationQuery) {
            this.validationQuery = validationQuery;
        }
    }
}
