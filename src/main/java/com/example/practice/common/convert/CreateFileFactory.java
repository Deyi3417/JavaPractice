package com.example.practice.common.convert;

import com.example.practice.common.convert.concrete.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author : HP
 * @date : 2023/4/18
 */
@Component
public class CreateFileFactory implements FileFactory{

    @Resource
    private PdfConvertToImg pdfConvertToImg;

    @Resource
    private WordConvertToImg wordConvertToImg;

    @Resource
    private PptCovertToImg pptCovertToImg;

    @Resource
    private ExcelConvertToImg excelConvertToImg;

    @Resource
    private TextConvertToImg textConvertToImg;


    @Override
    public FileConvert createConvert(String type) {
        if ("pdf".equals(type)) {
            return pdfConvertToImg;
        } else if ("docx".equals(type) || "doc".equals(type)) {
            return wordConvertToImg;
        } else if ("pptx".equals(type) || "ppt".equals(type)) {
            return pptCovertToImg;
        }else if ("xlsx".equals(type) || "xls".equals(type)) {
            return excelConvertToImg;
        }else if ("txt".equals(type)) {
            return textConvertToImg;
        } else {
            throw new IllegalArgumentException("Invalid File type");
        }
    }
}
