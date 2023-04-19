package com.example.practice.common.convert;

import com.example.practice.common.convert.concrete.PdfConvertToImg;

/**
 * @author : HP
 * @date : 2023/4/18
 */
public class CreateFileFactory implements FileFactory{
    @Override
    public FileConvert createConvert(String type) {
        if ("pdf".equals(type)) {
            return new PdfConvertToImg();
        } else if ("docx".equals(type)) {
            return null;
        } else {
            throw new IllegalArgumentException("Invalid File type");
        }
    }
}
