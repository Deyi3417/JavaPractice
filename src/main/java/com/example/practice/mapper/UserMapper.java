package com.example.practice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.practice.domain.User;
import com.example.practice.domain.request.PageSearchRequest;
import com.example.practice.domain.vo.ExportUserVO;
import com.example.practice.domain.vo.SafetyUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 刘德意
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2022-07-30 23:23:37
 * @Entity generator.domain.User
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 获取用户列表
     *
     * @return 用户列表
     */
    List<User> getUserList();

    /**
     * 根据ids获取用户
     *
     * @param ids 用户id数组
     * @return 对应的用户信息
     */
    List<User> getUserByIds02(Integer[] ids);

    /**
     * 根据ids获取用户
     *
     * @param listIds 用户id列表(列表可以做增删改查)
     * @return
     */
    List<User> getUserByIds03(List<Integer> listIds);

    /**
     * 获取导出用户列表
     *
     * @return 导出用户信息
     */
    List<ExportUserVO> getExportUserVO();

    List<SafetyUser> getUserListbyPage(Page<SafetyUser> page, PageSearchRequest params);
}




