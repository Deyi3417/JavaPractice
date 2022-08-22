package com.example.practice.controller;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
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
 * @date 2022/8/14
 */
@RestController
@Slf4j
@RequestMapping("/vue")
public class VueLearningController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public BasicResponse<List<User>> userList() {
        List<User> userList = userService.getUserList();
        return ResultUtils.success(userList);
    }
}
