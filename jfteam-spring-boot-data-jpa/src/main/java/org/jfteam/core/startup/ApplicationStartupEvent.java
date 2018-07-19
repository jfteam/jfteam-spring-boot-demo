package org.jfteam.core.startup;

import org.jfteam.core.holder.AppContextHolder;
import org.jfteam.core.ioc.SelfInjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-07-19
 * Time: 下午11:18
 */
@Component
public class ApplicationStartupEvent implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationStartupEvent.class);

    @Override
    public void run(String... strings) throws Exception {
        final Map<String, SelfInjectService> beansOfType = AppContextHolder.getContext().getBeansOfType(SelfInjectService.class);
        beansOfType.forEach((k, v) -> {
            LOGGER.info("{} class inject self.", k);
            v.setSelf(v);
        });
    }
}
