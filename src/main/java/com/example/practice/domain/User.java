package com.example.practice.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户表
 * @author 刘德意
 * @TableName user
 * @date 2022-07-30
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 用户id(主键)
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    @TableField(value = "username")
    private String username;

    /**
     * 登录账号
     */
    @TableField(value = "userAccount")
    private String userAccount;

    /**
     * 用户头像
     */
    @TableField(value = "avatarUrl")
    private String avatarUrl;

    /**
     * 性别
     */
    @TableField(value = "gender")
    private Integer gender;

    /**
     * 用户密码
     */
    @TableField(value = "userPassword")
    private String userPassword;

    /**
     * 电话
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户状态0-正常

     */
    @TableField(value = "userStatus")
    private Integer userStatus;

    /**
     * 创建时间
     */
    @TableField(value = "createTime")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(value = "updateTime")
    private Date updateTime;

    /**
     * 是否删除(逻辑删除0 1)
     */
    @TableField(value = "isDelete")
    private Integer isDelete;

    /**
     * 用户角色0-普通用户 1-管理员
     */
    @TableField(value = "userRole")
    private Integer userRole;

    /**
     * 星球编号
     */
    @TableField(value = "planetCode")
    private String planetCode;

    /**
     * 标签列表
     */
    @TableField(value = "tags")
    private String tags;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}