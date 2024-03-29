package com.example.practice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.practice.domain.User;
import com.example.practice.domain.request.PageSearchRequest;
import com.example.practice.domain.vo.ExportUserVO;
import com.example.practice.domain.vo.SafetyUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 刘德意
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2022-07-30 23:23:37
 */
public interface UserService extends IService<User> {


    /**
     * 获取用户列表
     *
     * @return {@link List}<{@link User}>
     */
    List<User> getUserList();

    /**
     * 获取用户列表-带条件查询
     *
     * @param page    用户id数组
     * @param request 用户id数组
     * @return 用户列表
     */
    List<SafetyUser> getUserList(Page<SafetyUser> page, PageSearchRequest request);

    /**
     * 根据ids获取用户
     *
     * @param ids 用户id数组
     * @return 对应的用户信息
     */
    List<User> getUserByIds(Integer[] ids);

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
     * @param ids 用户id数组
     * @return 对应的用户信息
     */
    List<User> getUserByIds03(Integer[] ids);

    /**
     * 获取导出用户列表
     *
     * @return 导出用户信息
     */
    List<ExportUserVO> getExportUserVO();

    /**
     * 根据标签搜索用户信息
     *
     * @param tagNameList 标签列表
     * @return 用户信息
     */
    List<SafetyUser> searchUserByTags(List<String> tagNameList);

    /**
     * 根据标签搜索用户信息
     *
     * @param tagNameList 标签列表
     * @return 用户信息
     */
    List<SafetyUser> searchUserByTags02(List<String> tagNameList);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request      session
     * @return 返回脱敏后的用户
     */
    SafetyUser userLogin(String userAccount, String userPassword, HttpServletRequest request);
}
