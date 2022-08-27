package com.example.practice.common.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author 刘德意
 * @date 2022/8/6
 * <p>
 * CommandLineRunner、ApplicationRunner 接口是在容器启动成功后的最后一步回调（类似开机自启动）
 * 实现CommandLineRunner接口；容器启动之后，加载实现类的逻辑资源，已达到完成资源初始化的任务；
 */
@Component
@Slf4j
@Order(1)
public class UserRunner implements CommandLineRunner {
    @Override
    public void run(String... args) {
        log.info("Start to loading user data");
        System.out.println("liudy23 测试启动加载初始数据--逻辑代码块");
        log.info("End CommandLineRunner......");
    }
}
