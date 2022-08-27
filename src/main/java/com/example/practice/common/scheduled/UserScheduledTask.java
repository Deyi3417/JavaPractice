package com.example.practice.common.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author 刘德意
 * @date 2022/8/6
 */


@Slf4j
@Component
@EnableAsync
public class UserScheduledTask {

    /*
        @Component 注解用于对那些比较中立的类进行注释
        相对与在持久层、业务层和控制层分别采用 @Repository、@Service 和 @Controller 对分层中的类进行注释

        @EnableScheduling 开启定时任务 有该注解才能够跑定时任务
        @Scheduled 添加定时任务  @Scheduled(cron = "0/2 * * * * ?") cron 表达式

        @EnableAsync 开启多线程

        @Async 在方法上使用该@Async注解，申明该方法是一个异步任务
        在类上面使用该@Async注解，申明该类中的所有方法都是异步任务；
        要想使用异步任务，需要在主类上开启异步配置，即，配置上@EnableAsync注解；
     */

    @Async
    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduledTask01() {
        log.info("Start to scheduledTask");
        System.out.println("开启定时任务（0/2 * * * * ?）每两秒执行一次  " + "\r\n线程：" + Thread.currentThread().getName());
        log.info("end to scheduledTask");
    }

    @Async
    @Scheduled(cron = "0/4 * * * * ?")
    public void scheduledTask02() {
        log.info("Start to scheduledTask");
        System.out.println("开启定时任务02（0/4 * * * * ?）每四秒执行一次  " + "\r\n线程：" + Thread.currentThread().getName());
        log.info("end to scheduledTask");
    }
}
