package com.example.practice.service.impl;

import com.alibaba.excel.util.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practice.common.ajax.ErrorCode;
import com.example.practice.common.exception.BusinessException;
import com.example.practice.common.mapstruct.basic.UserConvert;
import com.example.practice.domain.User;
import com.example.practice.domain.vo.ExportUserVO;
import com.example.practice.domain.vo.SafetyUser;
import com.example.practice.mapper.UserMapper;
import com.example.practice.service.UserService;
import com.example.practice.util.FileUtil;
import com.example.practice.util.RedisUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 刘德意
 * @description 针对表【user(用户表)】的数据库操作Service实现
 * @createDate 2022-07-30 23:23:37
 */
@Service
@Slf4j
@EnableScheduling
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisUtil redisUtil;

    private static final String USER_LIST_KEY = "user:suyao";

    @Override
    public List<User> getUserList() {

        List<User> userList = (List<User>) redisUtil.getHashValue(USER_LIST_KEY, "all");
        if (userList == null) {
            userList = this.list();
            redisUtil.setHashValue(USER_LIST_KEY,"all", userList);
        }
        return userList;
    }

    @Override
    public List<User> getUserByIds(Integer[] userIds) {
        List<User> users = this.baseMapper.selectList(new QueryWrapper<User>().eq("is_delete", 0).in("id", userIds));
        if (users == null || users.size() == 0) {
            throw new BusinessException(ErrorCode.NO_OBTAIN_DATA, "数据库该id没有数据");
        }
        return users;
    }

    @Override
    public List<User> getUserByIds02(Integer[] ids) {
        return this.baseMapper.getUserByIds02(ids);
    }

    @Override
    public List<User> getUserByIds03(Integer[] ids) {
        List<Integer> listIds = Arrays.asList(ids);
        return this.baseMapper.getUserByIds03(listIds);
    }

    @Override
    public List<ExportUserVO> getExportUserVO() {
        return this.baseMapper.getExportUserVO();
    }

    /**
     * SQL查询 在数据库处理
     *
     * @param tagNameList 标签 json 列表
     * @return
     */
    @Override
    public List<SafetyUser> searchUserByTags(List<String> tagNameList) {
        if (CollectionUtils.isEmpty(tagNameList)) {
            throw new BusinessException(ErrorCode.PARAM_NULL_ERROR);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        for (String tagName : tagNameList) {
            wrapper = wrapper.like("tags", tagName);
        }
        List<User> list = this.list(wrapper);
        return UserConvert.INSTANCE.toSafetyUserList(list);
    }

    /**
     * 内存查询 在内存中处理，优点时比较灵活，可以通过并发进一步优化
     *
     * @param tagNameList 标签 json 列表
     * @return
     */
    @Override
    public List<SafetyUser> searchUserByTags02(List<String> tagNameList) {
        if (CollectionUtils.isEmpty(tagNameList)) {
            throw new BusinessException(ErrorCode.PARAM_NULL_ERROR);
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("is_delete", 0);
        List<User> users = userMapper.selectList(wrapper);

        Gson gson = new Gson();
        List<User> collect = users.stream().filter(user -> {
            String tagsStr = user.getTags();
            // 使用 gson 反序列化 将json 转为java对象
            Set<String> tempTagsName = gson.fromJson(tagsStr, new TypeToken<Set<String>>() {
            }.getType());
            // 判空
            tempTagsName = Optional.ofNullable(tempTagsName).orElse(new HashSet<>());
            for (String tagName : tagNameList) {
                if (!tempTagsName.contains(tagName)) {
                    // 如果true 保留，如果false去除
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
        return UserConvert.INSTANCE.toSafetyUserList(collect);
    }

    @Override
    public SafetyUser userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 查询用户是否存在
        QueryWrapper<User> entity = new QueryWrapper<>();
        entity.eq("user_account", userAccount);
        entity.eq("user_password", userPassword);
        User user = userMapper.selectOne(entity);
        if (user == null) {
            log.info("user login failed, userAccount cannot match userPassword");
        }
        return UserConvert.INSTANCE.toSafetyUser(user);
    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void updateCache() {
        log.info("liudy23 is so handsoem==={}",DateUtils.format(new Date(), DateUtils.DATE_FORMAT_19));
//        List<User> userList = userMapper.getUserList();
//        redisTemplate.opsForHash().put(USER_LIST_KEY, "all", userList);
    }
}




