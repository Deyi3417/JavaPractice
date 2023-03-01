package com.example.practice.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 脱敏用户
 *
 * @author : liudy23
 * @data : 2023/3/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("脱敏用户信息")
public class SafetyUser implements Serializable {

    private static final long serialVersionUID = 2460849112286606889L;

    @ApiModelProperty("用户id(主键)")
    private Long id;

    @ApiModelProperty("昵称")
    private String username;

    @ApiModelProperty("登录账号")
    private String userAccount;

    @ApiModelProperty("用户头像")
    private String avatarUrl;

    @ApiModelProperty("性别")
    private Integer gender;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("用户状态 0-正常")
    private Integer userStatus;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("用户角色0-普通角色，1-管理员")
    private Integer userRole;

    @ApiModelProperty("用户标签")
    private String tags;
}
