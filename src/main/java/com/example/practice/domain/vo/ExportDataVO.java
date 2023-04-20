package com.example.practice.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : HP
 * @date : 2023/4/17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ColumnWidth(20)
public class ExportDataVO implements Serializable {

    private static final long serialVersionUID = 3292088200661432702L;

    @ExcelProperty("序号")
    private Integer id;

    @ExcelProperty("随机id")
    private String uuid;

    @ExcelProperty("姓名")
    private String name;

    @ExcelProperty("日期")
    private String date;

}
