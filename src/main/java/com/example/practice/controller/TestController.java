package com.example.practice.controller;

import com.example.practice.common.ajax.AjaxResult;
import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ErrorCode;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.common.exception.BusinessException;
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
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;


    @GetMapping("/getByIds")
    public AjaxResult getUserByIds(Integer[] ids) {
        log.info("根据ids获取用户执行了");
        if (ids.length == 0) {
            throw new BusinessException(ErrorCode.PARAM_NULL_ERROR,"哪个参数的异常，description给前端使用");
        }
        List<User> users = userService.getUserByIds(ids);
        if (users.size() > 0) {
            return AjaxResult.success(users);
        } else {
            return AjaxResult.error(200, "获取用户消息失败");
        }
    }

    @GetMapping("/test02")
    public BasicResponse test02(Integer[] ids) {
        log.info("根据ids获取用户执行了");
        List<User> users = userService.getUserByIds(ids);
        if (users.size() > 0) {
            return ResultUtils.success(users);
        } else {
            return ResultUtils.error(ErrorCode.NO_OBTAIN_DATA);
        }

    }
}
