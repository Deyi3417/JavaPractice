package com.example.practice.service.impl;

import com.aspose.words.ImageType;
import com.example.practice.common.config.properties.BasicProperties;
import com.example.practice.common.convert.CreateFileFactory;
import com.example.practice.common.convert.FileConvert;
import com.example.practice.common.convert.concrete.TextConvertToImg;
import com.example.practice.service.FileHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

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

    @Resource
    private TextConvertToImg textConvertToImg;

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
    public void reviewTextFile(HttpServletResponse response, File file) {
        try {
            textConvertToImg.covertToImgStream(file);
            log.info("可以转图片吗？");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
