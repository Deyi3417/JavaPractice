package com.example.practice.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : liudy23
 * @data : 2023/3/5
 */
@Data
@ApiModel(value = "星球用户信息")
public class PlantUserInfo implements Serializable {

    private static final long serialVersionUID = 6552761453652690156L;

    @TableField(value = "星球编号")
    @ExcelProperty(value = "星球编号")
    private String planetCode;

    @TableField(value = "用户昵称")
    @ExcelProperty(value = "用户昵称")
    private String username;
}
