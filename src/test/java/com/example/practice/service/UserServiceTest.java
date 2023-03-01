package com.example.practice.service;

import com.example.practice.domain.vo.SafetyUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : liudy23
 * @data : 2023/3/1
 */
@SpringBootTest
@Slf4j
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void searchUserByTags() {
        List<String> tagNameList = Arrays.asList("python");
        List<SafetyUser> safetyUsers = userService.searchUserByTags(tagNameList);
        log.info("{}==安全用户：{}", DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"),safetyUsers);
    }

    @Test
    void searchUserByTags02() {
        List<String> tagNameList = Arrays.asList("c++");
        List<SafetyUser> safetyUsers = userService.searchUserByTags02(tagNameList);
        log.info("{}==安全用户：{}", DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss"),safetyUsers);
    }
}