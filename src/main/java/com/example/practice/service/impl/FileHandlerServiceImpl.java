package com.example.practice.service.impl;

import com.example.practice.common.config.properties.BasicProperties;
import com.example.practice.common.convert.CreateFileFactory;
import com.example.practice.common.convert.FileConvert;
import com.example.practice.service.FileHandlerService;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 文件处理实现类
 *
 * @author : HP
 * @date : 2023/4/18
 */
@Service
@Slf4j
public class FileHandlerServiceImpl implements FileHandlerService {

    private static final String SAVE_PATH = "D:\\tmp\\usercenter\\saveFile\\2023-03-17\\Temp.pdf";

    @Resource
    private BasicProperties basicProperties;
    @Autowired
    private CreateFileFactory createFileFactory;

    @Override
    public void fileToImg(File file, HttpServletResponse response, String fileType) {
        response.setContentType("image/png");
        FileConvert convert = createFileFactory.createConvert(fileType);
        BufferedImage bufferedImage = convert.covertToImgStream(file);
        ServletOutputStream os = null;
        if (bufferedImage != null) {
            try {
                os = response.getOutputStream();
                ImageIO.write(bufferedImage, "png", os);
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    @Override
    public void wordToPdf(HttpServletResponse response, File file) {
        XWPFDocument xwpfDocument = null;
        OutputStream outputStream = null;
        try {
            xwpfDocument = new XWPFDocument(new FileInputStream(file));
            PdfOptions pdfOptions = PdfOptions.create();
            outputStream = response.getOutputStream();
            PdfConverter.getInstance().convert(xwpfDocument, outputStream, pdfOptions);
            outputStream.close();
            xwpfDocument.close();
            log.info("pdf转word成功");
        } catch (IOException e) {
            log.info("word to pdf is error:{}", e.getMessage());
        } finally {
            try {
                if (xwpfDocument != null) {
                    xwpfDocument.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void wordToPdf(File file) {
        XWPFDocument xwpfDocument = null;
        FileOutputStream outputStream = null;
        try {
            xwpfDocument = new XWPFDocument(new FileInputStream(file));
            PdfOptions pdfOptions = PdfOptions.create();
            outputStream = new FileOutputStream(basicProperties.getFileTempPdf());
            PdfConverter.getInstance().convert(xwpfDocument, outputStream, pdfOptions);
            outputStream.close();
            xwpfDocument.close();
            log.info("pdf转word成功");
        } catch (IOException e) {
            log.info("word to pdf is error:{}", e.getMessage());
        } finally {
            try {
                if (xwpfDocument != null) {
                    xwpfDocument.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
