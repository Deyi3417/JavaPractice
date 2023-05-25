package com.example.practice.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : liudy23
 * @data : 2023/5/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "分页、模糊搜搜等请求")
public class PageSearchRequest implements Serializable {

    private static final long serialVersionUID = -2889659905181071261L;

    @ApiModelProperty(value = "关键字")
    private String searchText;

    @ApiModelProperty(value = "当前页码")
    private int current;

    @ApiModelProperty(value = "每页显示数量")
    private int size;

    @ApiModelProperty(value = "用户状态")
    private String status;
}
