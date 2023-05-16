package com.example.practice.common.convert.concrete;

import com.example.practice.common.config.properties.BasicProperties;
import com.example.practice.common.convert.FileConvert;
import com.example.practice.service.FileHandlerService;
import com.example.practice.util.FileUtil;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author : HP
 * @date : 2023/4/18
 */
@Slf4j
@Service
public class WordConvertToImg extends FileConvert {

    @Autowired
    private PdfConvertToImg pdfConvertToImg;

    @Resource
    private BasicProperties basicProperties;

    private static final String savePath = "D:\\tmp\\usercenter\\saveFile\\2023-03-17\\testTemp.pdf";
    @Override
    public BufferedImage covertToImgStream(File file) {
        try {
            FileUtil.wordToPdf(file);
            File tempFile = new File(basicProperties.getFileTempPdf());
            return pdfConvertToImg.covertToImgStream(tempFile);
        } catch (Exception e) {
            log.info("covertToImgStream is error: {}", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
