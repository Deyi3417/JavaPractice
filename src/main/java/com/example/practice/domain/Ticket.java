package com.example.practice.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 不合格票表
 * @TableName ticket
 */
@TableName(value ="ticket")
@Data
public class Ticket implements Serializable {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 票编号
     */
    @TableField(value = "ticket_code")
    private String ticketCode;

    /**
     * 票等级
     */
    @TableField(value = "ticket_level")
    private Integer ticketLevel;

    /**
     * 逻辑删除标志
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 票处理方式
     */
    @TableField(value = "handle_way")
    private Integer handleWay;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 创建人id
     */
    @TableField(value = "create_id")
    private Long createId;

    /**
     * 创建人姓名
     */
    @TableField(value = "create_name")
    private String createName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改人id
     */
    @TableField(value = "modify_id")
    private Long modifyId;

    /**
     * 修改人姓名
     */
    @TableField(value = "modify_name")
    private String modifyName;

    /**
     * 修改时间
     */
    @TableField(value = "modify_time")
    private Date modifyTime;

    /**
     * 票的来源
     */
    @TableField(value = "source")
    private String source;

    /**
     * 票的价格(小数点后两位)
     */
    @TableField(value = "price")
    private Double price;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}