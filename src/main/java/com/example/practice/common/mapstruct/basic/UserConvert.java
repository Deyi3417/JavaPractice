package com.example.practice.common.mapstruct.basic;

import com.example.practice.domain.User;
import com.example.practice.domain.vo.SafetyUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author : liudy23
 * @data : 2023/3/1
 */
@Mapper(componentModel = "spring")
public interface UserConvert {

    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    /**
     * 单个用户脱敏转为安全用户
     *
     * @param user 用户信息
     * @return 安全用户
     */
    SafetyUser toSafetyUser(User user);

    /**
     * 多个用户脱敏转为安全用户
     *
     * @param users 多个用户信息
     * @return 安全用户
     */
    List<SafetyUser> toSafetyUserList(List<User> users);
}
