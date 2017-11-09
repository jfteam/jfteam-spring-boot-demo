package com.jfteam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * SpringBoot应用程序启动类
 */
@SpringBootApplication
public class AppStarter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AppStarter.class, args);
        LOGGER.info("Application: {} was started!..........", context.getApplicationName());
    }
}
