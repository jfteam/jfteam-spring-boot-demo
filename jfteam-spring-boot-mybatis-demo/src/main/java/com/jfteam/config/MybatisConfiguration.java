package com.jfteam.config;

import com.jfteam.framework.page.PageInterceptor;
import net.sf.log4jdbc.Log4jdbcProxyDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2017-10-29
 * Time: 下午7:13
 */
@Configuration
@MapperScan("com.jfteam.dao")
public class MybatisConfiguration {

    @Bean
    @Profile("dev")
    public DataSource proxyDataSource(DataSourceProperties dataSourceProperties) {
        DataSourceBuilder factory = DataSourceBuilder
                .create(dataSourceProperties.getClassLoader())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword());
        DataSource realDataSource = factory.build();
        return new Log4jdbcProxyDataSource(realDataSource);
    }

    @Bean
    @Profile({"sit", "uat", "prod"})
    public DataSource realDataSource(DataSourceProperties dataSourceProperties) {
        DataSourceBuilder factory = DataSourceBuilder
                .create(dataSourceProperties.getClassLoader())
                .url(dataSourceProperties.getUrl())
                .username(dataSourceProperties.getUsername())
                .password(dataSourceProperties.getPassword());
        return factory.build();
    }

    @Bean
    public PageInterceptor pageInterceptor() {
        return new PageInterceptor();
    }
}
