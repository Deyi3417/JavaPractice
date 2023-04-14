package com.example.practice.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : HP
 * @date : 2023/4/14
 */
public interface PictureService {
    void addWatermarkToImage(String srcFile, String destFile, String watermark, int x, int y) throws IOException;

    void addWatermarkToImage(String srcFile, String destFile, String watermark, int x, int y, HttpServletResponse response) throws IOException;
}
