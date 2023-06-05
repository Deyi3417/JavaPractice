package com.example.practice.service;

import com.example.practice.domain.vo.SafetyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.ExecutionException;

/**
 * @author : liudy23
 * @data : 2023/6/5
 */
public interface TestService {


    /**
     * 异步处理根据用户id获取用户
     *
     * @param id
     * @return
     */
    SafetyUser getUserById(String id);
}
