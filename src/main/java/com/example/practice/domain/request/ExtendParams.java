package com.example.practice.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : HP
 * @date : 2023/4/19
 */
@Data
@ApiModel(value = "继承参数",description = "ExtendParams")
public class ExtendParams extends UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 7452990671239052719L;

    @ApiModelProperty(value = "planetCode", name = "测试code")
    private Integer planetCode;

    @ApiModelProperty(value = "address", name = "测试地址")
    private String address;
}
