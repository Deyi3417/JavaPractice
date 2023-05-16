package com.example.practice.service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author : HP
 * @date : 2023/4/18
 */
public interface FileHandlerService {
    /**
     * 文件转文件
     * @param file 目标文件
     * @param response
     * @param fileType 文件类型
     */
    void fileToImg(File file, HttpServletResponse response, String fileType);

}
