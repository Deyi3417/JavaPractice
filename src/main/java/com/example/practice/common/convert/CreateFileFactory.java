package com.example.practice.common.convert;

import com.example.practice.common.convert.concrete.PdfConvertToImg;
import com.example.practice.common.convert.concrete.WordConvertToImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : HP
 * @date : 2023/4/18
 */
@Component
public class CreateFileFactory implements FileFactory{

    @Autowired
    private PdfConvertToImg pdfConvertToImg;

    @Autowired
    private WordConvertToImg wordConvertToImg;

    @Override
    public FileConvert createConvert(String type) {
        if ("pdf".equals(type)) {
            return pdfConvertToImg;
        } else if ("docx".equals(type) || "doc".equals(type)) {
            return wordConvertToImg;
        } else {
            throw new IllegalArgumentException("Invalid File type");
        }
    }
}
