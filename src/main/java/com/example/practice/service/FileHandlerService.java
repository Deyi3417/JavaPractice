package com.example.practice.service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author : HP
 * @date : 2023/4/18
 */
public interface FileHandlerService {
    void fileToImg(File file, HttpServletResponse response, String fileType);

}
