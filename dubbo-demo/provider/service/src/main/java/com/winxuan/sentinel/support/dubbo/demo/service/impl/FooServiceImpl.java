package com.winxuan.sentinel.support.dubbo.demo.service.impl;

import com.winxuan.sentinel.support.dubbo.demo.service.FooService;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author xiejihan
 * @date 2018-08-30
 */
@Service("fooService")
public class FooServiceImpl implements FooService {

    @Override
    public String hello(String name) {
        long start = System.currentTimeMillis();

        if (true) throw new RuntimeException("debug");

        try {
            Thread.sleep(new Random().nextInt(3000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello " + name + "[" + (System.currentTimeMillis() - start) + "ms]");
        return "hello " + name + "[" + (System.currentTimeMillis() - start) + "ms]";
    }
}
