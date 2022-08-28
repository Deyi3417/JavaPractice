package com.example.practice.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 刘德意
 * @date 2022/8/28
 */
@Data
@ApiModel(description = "TicketDTO")
public class TicketDTO {

    @ApiModelProperty(value = "不合格票ID", name = "ID票")
    @NotNull(message = "不合格票ID不能为空")
    private Long id;

    @ApiModelProperty(value = "不合格票等级", name = "Level等级")
    @NotNull(message = "不合格票等级不能为空")
    private Integer ticketLevel;

    @ApiModelProperty(value = "不合格票处理方式", name = "Way处理方式")
    @NotNull(message = "不合格票处理方式不能为空")
    private Integer handleWay;
}
