package com.example.practice.service.impl;

import com.example.practice.common.mapstruct.basic.UserConvert;
import com.example.practice.domain.User;
import com.example.practice.domain.vo.SafetyUser;
import com.example.practice.service.TestService;
import com.example.practice.service.UserService;
import com.example.practice.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author : liudy23
 * @data : 2023/6/5
 */
@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Resource
    private UserService userService;

    @Resource
    private UserConvert userConvert;

    @Override
    public SafetyUser getUserById(String id) {
        // 创建一个CompletableFuture对象，表示异步操作
        System.out.println("当前系统时间impl01: " + DateUtil.getDefaultTime());
        CompletableFuture<SafetyUser> future = CompletableFuture.supplyAsync(() -> {
            try {
//                TimeUnit.SECONDS.sleep(15);
                Thread.sleep(10000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("当前系统时间impl02: " + DateUtil.getDefaultTime());
            User user = userService.getById(id);
            System.out.println("safetyUser:" + user);
            return userConvert.toSafetyUser(user);
        });
        System.out.println("当前系统时间impl03: " + DateUtil.getDefaultTime());
        System.out.println("user:");
        return userConvert.toSafetyUser(userService.getById(6));
    }
}
