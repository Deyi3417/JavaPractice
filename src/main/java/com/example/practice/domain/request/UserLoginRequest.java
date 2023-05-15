package com.example.practice.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author HP
 * @create 2022/5/1 20:56
 */
@Data
@ApiModel(value = "用户登录请求类", description = "UserLoginRequest")
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -6604267875589845076L;

    /**
     * 用户账户
     */
    @NotNull(message = "账户不能为空")
    @ApiModelProperty(value = "用户账号", name = "username")
    private String username;

    /**
     * 用户密码
     */
    @NotNull(message = "密码不能为空")
    @ApiModelProperty(value = "用户密码", name = "password")
    private String password;
}
