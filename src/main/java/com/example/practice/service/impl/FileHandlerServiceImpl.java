package com.example.practice.service.impl;

import com.example.practice.common.convert.CreateFileFactory;
import com.example.practice.common.convert.FileConvert;
import com.example.practice.service.FileHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 文件处理实现类
 *
 * @author : HP
 * @date : 2023/4/18
 */
@Service
@Slf4j
public class FileHandlerServiceImpl implements FileHandlerService {


    /**
     * 文件转图片
     *
     * @param response
     */
    public void fileToPicture(HttpServletResponse response) {
        // 文件流转图片流预览



    }

    @Override
    public void fileToImg(File file, HttpServletResponse response, String fileType) {
        response.setContentType("image/png");
        CreateFileFactory factory = new CreateFileFactory();
        FileConvert convert = factory.createConvert(fileType);
        BufferedImage bufferedImage = convert.covertToImgStream(file);
        ServletOutputStream os = null;
        if (bufferedImage != null) {
            try {
                os = response.getOutputStream();
                ImageIO.write(bufferedImage,"png", os);
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
}
