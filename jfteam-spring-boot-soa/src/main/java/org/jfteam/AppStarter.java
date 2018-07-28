package org.jfteam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * Hello world!
 */
@SpringBootApplication
public class AppStarter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStarter.class);

    public static void main(String[] args) {
        final SpringApplication springApplication = new SpringApplication(AppStarter.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        final ConfigurableApplicationContext run = springApplication.run(args);
        final ConfigurableEnvironment environment = run.getEnvironment();
        final String applicationName = environment.getProperty("spring.application.name");
        LOGGER.info("Application: {} was started!..........", applicationName);
    }
}
