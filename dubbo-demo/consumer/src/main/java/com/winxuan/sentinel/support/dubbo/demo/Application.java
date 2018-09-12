package com.winxuan.sentinel.support.dubbo.demo;


import com.alibaba.csp.sentinel.concurrent.NamedThreadFactory;
import com.winxuan.sentinel.support.SentinelSupportConstant;
import com.winxuan.sentinel.support.dubbo.demo.service.FooService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.JmsAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author cdfive
 * @date 2018-08-29
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.winxuan"}, exclude = {JmsAutoConfiguration.class})
@ImportResource("classpath:conf/context.xml")
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        log.info(SentinelSupportConstant.LOG_PRIFEX + "dubbo-demo[consumer] application started!");

        test(ctx);
    }

    /**
     * 测试调用provider的方法
     */
    public static void test(ConfigurableApplicationContext ctx) {
        FooService fooService = ctx.getBean(FooService.class);

        ExecutorService pool = Executors.newFixedThreadPool(30);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 100; i++) {
            final int index = i + 1;
            pool.submit(() -> {
                try {
                    String result = fooService.hello("cdfive" + "[" + index + "]");
                    System.out.println(result);
                } catch (Exception e) {
//                    e.printStackTrace();
                    System.out.println("[exception]" + e.getClass().getName() + "[" + index + "]");
                }
            });
        }

        try {
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.MINUTES);
            System.out.println("done " + (System.currentTimeMillis() - start) / 1000.0 + "s");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}