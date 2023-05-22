package com.example.practice.common.convert.concrete;

import com.example.practice.common.convert.FileConvert;
import com.example.practice.utils.FileUtil;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Excel文件转图片流
 *
 * @author : HP
 * @date : 2023/5/18
 */
@Component
public class ExcelConvertToImg extends FileConvert {

    private static final String OUTPUT_EXCEL_TO_PDF = "D:\\tmp\\usercenter\\saveFile\\2023-03-17\\outputExcelTemp.pdf";

    @Override
    public BufferedImage covertToImgStream(File file) {
        FileUtil.asposeExcelToPdf(file, OUTPUT_EXCEL_TO_PDF);
        return FileUtil.getImagStream(new File(OUTPUT_EXCEL_TO_PDF));
    }
}
