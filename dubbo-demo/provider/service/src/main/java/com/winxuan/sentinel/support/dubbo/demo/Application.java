package com.winxuan.sentinel.support.dubbo.demo;


import com.winxuan.sentinel.support.SentinelSupportConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author cdfive
 * @date 2018-08-29
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.winxuan"}, exclude = {JmsAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class, JdbcTemplateAutoConfiguration.class})
@ImportResource("classpath:conf/context.xml")
public class Application {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
        log.info(SentinelSupportConstant.LOG_PRIFEX + "dubbo-demo[provider] application started!");
    }
}