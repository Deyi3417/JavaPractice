package com.example.practice.controller;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.domain.vo.SafetyUser;
import com.example.practice.service.TestService;
import com.example.practice.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : liudy23
 * @data : 2023/6/5
 */
@RestController
@Slf4j
@Api(tags = "测试控制器02-0605")
@RequestMapping("/test02")
public class TestController02 {

    @Resource
    private TestService testService;

    /**
     * 异步处理，根据id获取用户
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserById/{id}")
    @ApiOperation("根据用户id获取用户")
    public BasicResponse<SafetyUser> getUserById(@PathVariable("id") String id) {
        SafetyUser user = testService.getUserById(id);
        System.out.println("当前系统时间controller: " + DateUtil.getDefaultTime());
        return ResultUtils.success(user);
    }

}
