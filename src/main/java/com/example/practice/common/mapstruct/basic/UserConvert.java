package com.example.practice.common.mapstruct.basic;

import com.example.practice.common.constant.enums.UserEnums;
import com.example.practice.domain.User;
import com.example.practice.domain.vo.ExportUserVO;
import com.example.practice.domain.vo.SafetyUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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
    @Mappings({
            @Mapping(target = "genderName", expression = "java(getGenderName(user.getGender()))"),
            // @Mapping(target = "avatarUrl", ignore = true)
    })
    SafetyUser toSafetyUser(User user);

    /**
     * 多个用户脱敏转为安全用户
     *
     * @param users 多个用户信息
     * @return 安全用户
     */
    List<SafetyUser> toSafetyUserList(List<User> users);

    List<ExportUserVO> toExpotrUserList(List<User> users);

    /**
     * 根据性别编码获取名称
     *
     * @param gender
     * @return
     */
    default String getGenderName(Integer gender) {
        return UserEnums.UserGenderEnum.byCode(gender).getName();
    }
}
