package com.jfteam.config;

import com.jfteam.framework.datasource.DataSourceType;
import com.jfteam.framework.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
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
//@EnableConfigurationProperties(DynamicDataSourceProperties.class)
public class DynamicDataSourceConfiguration {

//    private BasicDataSource parentDataSource(DynamicDataSourceProperties properties) {
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setMaxWaitMillis(properties.getMaxWaitMillis());
//        basicDataSource.setInitialSize(properties.getInitialSize());
//        basicDataSource.setMaxTotal(properties.getMaxTotal());
//        basicDataSource.setMaxIdle(properties.getMaxIdle());
//        basicDataSource.setMinIdle(properties.getMinIdle());
//        basicDataSource.setValidationQuery(properties.getValidationQuery());
//        return basicDataSource;
//    }
//
//    @Bean(DataSourceType.DATASOURCE_PRIMARY)
//    @Primary
//    public DataSource dataSourcePrimary(DynamicDataSourceProperties properties) {
//        BasicDataSource dataSource = parentDataSource(properties);
//        dataSource.setDriverClassName(properties.getPrimary().getDriverClassName());
//        dataSource.setUrl(properties.getPrimary().getUrl());
//        dataSource.setUsername(properties.getPrimary().getUsername());
//        dataSource.setPassword(properties.getPrimary().getPassword());
//        dataSource.setConnectionProperties(properties.getPrimary().getConnectionProperties());
//        return dataSource;
//    }
//
//    @Bean(DataSourceType.DATASOURCE_SLAVE)
//    public DataSource dataSourceSlave(DynamicDataSourceProperties properties) {
//        BasicDataSource dataSource = parentDataSource(properties);
//        dataSource.setDriverClassName(properties.getSlave().getDriverClassName());
//        dataSource.setUrl(properties.getSlave().getUrl());
//        dataSource.setUsername(properties.getSlave().getUsername());
//        dataSource.setPassword(properties.getSlave().getPassword());
//        dataSource.setConnectionProperties(properties.getSlave().getConnectionProperties());
//        return dataSource;
//    }

    @Bean(DataSourceType.DATASOURCE_PRIMARY)
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(DataSourceType.DATASOURCE_SLAVE)
    @ConfigurationProperties(prefix = "spring.second-datasource")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DynamicDataSource dynamicDataSource(@Qualifier(DataSourceType.DATASOURCE_PRIMARY) DataSource primary,
                                               @Qualifier(DataSourceType.DATASOURCE_SLAVE) DataSource slave) {
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceType.DATASOURCE_PRIMARY, primary);
        dataSourceMap.put(DataSourceType.DATASOURCE_SLAVE, slave);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(primary);
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }
}
