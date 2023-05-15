package com.example.practice.domain.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 批量导入数据实体
 *
 * @author : HP
 * @date : 2023/4/24
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ApiModel(value = "导入数据表头字段")
public class ImportDeviceData implements Serializable {

    private static final long serialVersionUID = -5207056389909876088L;

    @ExcelProperty("设备名称")
    private String deviceName;

    @ExcelProperty("设备型号")
    private String deviceModel;

    @ExcelProperty("设备类型")
    private String deviceTypeName;

    @ExcelProperty("设备技术参数")
    private String deviceSpecification;

    @ExcelProperty("设备状态")
    private String deviceStatusName;

    @ExcelProperty("购买日期")
    private Date purchaseDate;

    @ExcelProperty("购买价值")
    private Double purchasePrice;

    @ExcelProperty("保管责任人姓名")
    private String custodianName;

    @ExcelProperty("保管责任人工号")
    private String custodianJobNumber;

    @ExcelProperty("是否检定")
    private String isInspectedName;

    @ExcelProperty("检定有效期")
    private Date validityPeriod;

}
