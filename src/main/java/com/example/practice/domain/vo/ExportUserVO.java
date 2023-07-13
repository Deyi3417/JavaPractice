package com.example.practice.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 刘德意
 * @date 2022/8/2
 */
@Data
public class ExportUserVO {

    /**
     * 用户id(主键)
     */
    @ExcelProperty(value = "序号", index = 0)
    private Long id;

    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称", index = 1)
    private String username;

    /**
     * 登录账号
     */
    @ExcelProperty(value = "账号", index = 2)
    private String userAccount;

    /**
     * 性别名称
     */
    @ExcelProperty(value = "性别", index = 3)
    private String genderName;

    /**
     * 电话
     */
    @ColumnWidth(30)
    @ExcelProperty(value = "电话", index = 4)
    private String phone;

    /**
     * 创建时间
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "创建时间", index = 5)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ColumnWidth(30)
    @ExcelProperty(value = "简介", index = 6)
    private String profile;
}
