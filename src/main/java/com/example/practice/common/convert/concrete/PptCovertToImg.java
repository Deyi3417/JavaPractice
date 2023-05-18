package com.example.practice.common.convert.concrete;

import com.example.practice.common.convert.FileConvert;
import com.example.practice.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * ppt文件转图片流
 *
 * @author : HP
 * @date : 2023/5/18
 */
@Component
public class PptCovertToImg extends FileConvert {

    private static final String OUTPUT_PPT_TO_PDF = "D:\\tmp\\usercenter\\tempFile\\pdf_file\\outputPptTemp.pdf";

    @Override
    public BufferedImage covertToImgStream(File file) {
        FileUtil.asposePptToPdf(file, OUTPUT_PPT_TO_PDF);
        return FileUtil.getImagStream(new File(OUTPUT_PPT_TO_PDF));
    }
}
