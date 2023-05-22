package com.example.practice.common.convert.concrete;

import com.example.practice.common.config.properties.BasicProperties;
import com.example.practice.common.convert.FileConvert;
import com.example.practice.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.ResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Properties;

/**
 * Word文件转图片流
 *
 * @author : HP
 * @date : 2023/4/18
 */
@Slf4j
@Service
public class WordConvertToImg extends FileConvert {

    private static final String OUTPUT_WORD_TO_PDF = "D:\\tmp\\usercenter\\tempFile\\pdf_file\\outputWordTemp.pdf";

    @Resource
    private BasicProperties basicProperties;

    @Override
    public BufferedImage covertToImgStream(File file) {
        try {
            FileUtil.asposeWordToPDF(file, OUTPUT_WORD_TO_PDF);
            File tempFile = new File(OUTPUT_WORD_TO_PDF);
            return FileUtil.getImagStream(tempFile);
        } catch (Exception e) {
            log.info("covertToImgStream is error: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        Properties properties = System.getProperties();
        System.out.println(properties);
    }
}
