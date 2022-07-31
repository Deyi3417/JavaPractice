package com.example.practice.controller;

import com.example.practice.domain.User;
import com.example.practice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘德意
 * @data 2022-07-30
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public List<User> getUser() {
        log.info("程序执行了");
        return userService.getUserList();
    }

    @GetMapping("/getByIds")
    public List<User> getUserByIds(Integer[] ids) {
        log.info("根据ids获取用户执行了");
        return userService.getUserByIds(ids);
    }

    @GetMapping("/getByIds02")
    public List<User> getUserByIds02(Integer[] ids) {
        log.info("根据ids 02获取用户执行了");
        return userService.getUserByIds02(ids);
    }

    @GetMapping("/getByIds03")
    public List<User> getUserByIds03(Integer[] ids) {
        log.info("根据ids 03获取用户执行了");
        return userService.getUserByIds03(ids);
    }
}
