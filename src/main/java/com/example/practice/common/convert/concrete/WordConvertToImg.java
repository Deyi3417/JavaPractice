package com.example.practice.common.convert.concrete;

import com.example.practice.common.convert.FileConvert;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import javax.annotation.Resource;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author : HP
 * @date : 2023/4/18
 */
public class WordConvertToImg extends FileConvert {
    @Resource
    private PdfConvertToImg pdfConvertToImg;

    @Override
    public BufferedImage covertToImgStream(File file) {
        String savePath = "D:\\tmp\\usercenter\\saveFile\\2023-03-17\\test1234.pdf";
        try {
            XWPFDocument xwpfDocument = new XWPFDocument(new FileInputStream(file));
            PdfOptions pdfOptions = PdfOptions.create();
            FileOutputStream outputStream = new FileOutputStream(new File(savePath));
            PdfConverter.getInstance().convert(xwpfDocument, outputStream, pdfOptions);
            // 关闭流
            outputStream.close();
            xwpfDocument.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return null;
    }
}
