package com.example.practice.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : liudy23
 * @data : 2023/3/5
 */
@Data
public class PlantUserInfo implements Serializable {

    private static final long serialVersionUID = 6552761453652690156L;

    @TableField(value = "星球编号")
    private String username;

    @TableField(value = "用户昵称")
    private String planetCode;
}
