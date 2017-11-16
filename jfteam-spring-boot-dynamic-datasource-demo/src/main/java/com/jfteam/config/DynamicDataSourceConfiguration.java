package com.jfteam.config;

import com.jfteam.framework.datasource.DataSourceType;
import com.jfteam.framework.datasource.DynamicDataSource;
import com.jfteam.framework.datasource.DynamicDataSourceProperties;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2017-11-15
 * Time: 下午9:36
 */
@Configuration
@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSourceConfiguration {

    private BasicDataSource parentDataSource(DynamicDataSourceProperties.JdbcProperties properties) {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setMaxWaitMillis(properties.getMaxWaitMillis());
        basicDataSource.setInitialSize(properties.getInitialSize());
        basicDataSource.setMaxTotal(properties.getMaxTotal());
        basicDataSource.setMaxIdle(properties.getMaxIdle());
        basicDataSource.setMinIdle(properties.getMinIdle());
        basicDataSource.setValidationQuery(properties.getValidationQuery());
        return basicDataSource;
    }

    @Bean(name = DataSourceType.DATASOURCE_MASTER)
    @Primary
    public DataSource masterDataSource(DynamicDataSourceProperties properties) {
        DynamicDataSourceProperties.JdbcProperties jdbcProperties = properties.getMaster();
        BasicDataSource dataSource = this.parentDataSource(jdbcProperties);
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());
        dataSource.setConnectionProperties(jdbcProperties.getConnectionProperties());
        return new Log4jdbcProxyDataSource(dataSource);
    }

    @Bean(name = DataSourceType.DATASOURCE_SLAVE)
    public DataSource slaveDataSource(DynamicDataSourceProperties properties) {
        DynamicDataSourceProperties.JdbcProperties jdbcProperties = properties.getSlave();
        BasicDataSource dataSource = parentDataSource(jdbcProperties);
        dataSource.setDriverClassName(jdbcProperties.getDriverClassName());
        dataSource.setUrl(jdbcProperties.getUrl());
        dataSource.setUsername(jdbcProperties.getUsername());
        dataSource.setPassword(jdbcProperties.getPassword());
        dataSource.setConnectionProperties(jdbcProperties.getConnectionProperties());
        return new Log4jdbcProxyDataSource(dataSource);
    }

//    @Bean(name = DataSourceType.DATASOURCE_MASTER)
//    @ConfigurationProperties(prefix = "spring.datasource.dynamic.master")
//    @Primary
//    public DataSource masterDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = DataSourceType.DATASOURCE_SLAVE)
//    @ConfigurationProperties(prefix = "spring.datasource.dynamic.slave")
//    public DataSource slaveDataSource() {
//        return DataSourceBuilder.create().build();
//    }

    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier(DataSourceType.DATASOURCE_MASTER) DataSource masterDataSource,
                                 @Qualifier(DataSourceType.DATASOURCE_SLAVE) DataSource slaveDataSource) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.DATASOURCE_MASTER, masterDataSource);
        dataSourceMap.put(DataSourceType.DATASOURCE_SLAVE, slaveDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }
}
