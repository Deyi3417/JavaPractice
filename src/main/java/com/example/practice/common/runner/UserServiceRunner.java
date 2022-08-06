package com.example.practice.common.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 代码执行的时候加载初始数据
 *
 * @author 刘德意
 * @date 2022-07-30
 */
@Slf4j
@Component
@Order(2)
public class UserServiceRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        log.info("Start loading initialize parameters");
        System.out.println("中间是逻辑代码");
        log.info("end loading initialize parameter");
    }
}
