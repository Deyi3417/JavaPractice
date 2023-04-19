package com.example.practice.common.convert;

import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author : HP
 * @date : 2023/4/18
 */
public abstract class FileConvert {

    /**
     * pdf转图片流
     *
     * @param file 输入文件
     * @return
     */
    public abstract BufferedImage covertToImgStream(File file);
}
