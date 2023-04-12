package com.example.practice.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author : liudy23
 * @data : 2023/4/8
 */
@Data
@ApiModel("测试")
public class RequestTestVO implements Serializable {

    private static final long serialVersionUID = 6554161056755788305L;

    @ApiModelProperty(value = "id", name = "主键id")
    private Integer id;

    @ApiModelProperty(value = "expectedEndTime", name = "时间")
    private Date expectedEndTime;
}
