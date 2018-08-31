package com.winxuan.sentinel.support.activemq.demo;

import com.winxuan.sentinel.support.SentinelSupportConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author cdfive
 * @date 2018-08-29
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.winxuan"}, exclude = {JmsAutoConfiguration.class})
@ImportResource("classpath:conf/context.xml")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        log.info(SentinelSupportConstant.LOG_PRIFEX + "activemq-demo application started!");
    }
}