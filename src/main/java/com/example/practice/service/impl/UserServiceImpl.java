package com.example.practice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.practice.common.ajax.ErrorCode;
import com.example.practice.common.exception.BusinessException;
import com.example.practice.domain.User;
import com.example.practice.domain.vo.ExportUserVO;
import com.example.practice.service.UserService;
import com.example.practice.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
* @author 刘德意
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2022-07-30 23:23:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public List<User> getUserList() {
        return this.baseMapper.getUserList();
    }

    @Override
    public List<User> getUserByIds(Integer[] userIds) {
        List<User> users = this.baseMapper.selectList(new QueryWrapper<User>().eq("is_delete", 0).in("id", userIds));
        if (users == null || users.size() == 0) {
            throw new BusinessException(ErrorCode.NO_OBTAIN_DATA,"数据库该id没有数据");
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
}




