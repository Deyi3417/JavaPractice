package com.example.practice.controller;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.common.config.properties.BasicProperties;
import com.example.practice.common.mapstruct.basic.UserConvert;
import com.example.practice.domain.User;
import com.example.practice.service.FileHandlerService;
import com.example.practice.service.TagService;
import com.example.practice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 在Java中，@RequiredArgsConstructor 是Lombok库提供的注解之一，用于自动生成一个包含被final修饰或标记为@NonNull的属性的构造函数。
 *
 * @author : HP
 * @date : 2023/7/18
 */
@RestController
@Slf4j
@RequestMapping("/liudy23/test")
@Api(tags = "学习测试-0718")
@RequiredArgsConstructor
public class TestController03 {

    private final UserService userService;

    @Resource
    private FileHandlerService fileHandlerService;

    @Resource
    private TagService tagService;

    @Resource
    private BasicProperties basicProperties;

    @Resource
    private UserConvert userConvert;

    @GetMapping("/getUser")
    @ApiOperation("获取用户-@RequiredArgsConstructor注解")
    private BasicResponse<List<User>> getUser() {
        List<User> list = userService.list();
        return ResultUtils.success(list);
    }
}
