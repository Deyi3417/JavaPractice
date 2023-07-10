package com.example.practice.controller;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.domain.User;
import com.example.practice.mapper.UserMapper;
import com.example.practice.service.TestService;
import com.example.practice.service.UserService;
import com.example.practice.utils.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : liudy23
 * @data : 2023/6/5
 */
@RestController
@Slf4j
@RequestMapping("/test02")
@Api(tags = "测试控制器02-0605")
public class TestController02 {

    @Resource
    private TestService testService;

    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Value("${oa.test_user}")
    private String oaTestUser;

    /**
     * 异步处理，根据id获取用户
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserById/{id}")
    @ApiOperation("根据用户id获取用户")
    public BasicResponse getUserById(@PathVariable("id") String id) {
        List<User> user = userMapper.selectList(new QueryWrapper<User>()
                .like("username","苏"));

        User user1 = userService.getById(4);
        Map<String, Object> map = new HashMap<>();
        map.put("user", new User());

        List<String> users = Arrays.asList(oaTestUser.split(","));
        if (!(oaTestUser.equals("all") || users.contains(Convert.toStr(3706)))) {
            System.out.println("测试获取配置文件信息：" + JSON.toJSONString(oaTestUser));
            System.out.println("测试获取配置文件信息2：" + JSON.toJSONString(users));
        } else {
            System.out.println("取反");
        }
        return ResultUtils.success(map);
    }



}
