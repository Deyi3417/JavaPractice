package com.example.practice.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.metadata.data.WriteCellData;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.Date;

/**
 * @author : liudy23
 * @data : 2023/4/12
 */
@Getter
@Setter
@EqualsAndHashCode
@ContentRowHeight(100)
@ColumnWidth(20)
public class ImageDemoData {
    @ColumnWidth(100/8)
    @ExcelProperty("文件名")
    private File file;
    @ExcelProperty("主键")
    private Integer id;
    private String userName;
    private Date time;
    @ExcelProperty("附件")
    @ColumnWidth(100/8)
    private WriteCellData<Object> writeCellDataFile;
}
