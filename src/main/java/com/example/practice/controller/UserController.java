package com.example.practice.controller;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.domain.User;
import com.example.practice.domain.vo.SafetyUser;
import com.example.practice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘德意
 * @data 2022-07-30
 */
@RestController
@Slf4j
@RequestMapping("/user")
@Api(tags = "用户表控制器")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation("获取用户列表")
    public List<User> getUser() {
        log.info("程序执行了");
        return userService.getUserList();
    }

    @GetMapping("/getByIds")
    @ApiOperation("根据用户id获取目标用户信息")
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

    @GetMapping("/search/tags")
    @ApiOperation("根据标签搜索用户")
    public BasicResponse<?> searchUsersByTags(@RequestParam List<String> tagNameList) {
        List<SafetyUser> safetyUsers = userService.searchUserByTags02(tagNameList);
        return ResultUtils.success(safetyUsers);
    }

}
