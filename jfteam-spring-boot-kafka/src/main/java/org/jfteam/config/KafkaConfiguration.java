package org.jfteam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: fengwenping
 * Date: 2018-04-26
 * Time: 下午10:55
 */
@Configuration
@EnableKafka
public class KafkaConfiguration {

    @KafkaListener(topics = {"logger"})
    public void listenLoggerQueue() {

    }
}
