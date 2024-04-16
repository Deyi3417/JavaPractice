package com.example.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 下载文件控制器
 * 注解 @RestController 它结合了@Controller和@ResponseBody两个注解的功能，
 * 用于定义一个控制器类，该类中的方法将处理HTTP请求并返回响应数据。
 *
 * @author : liudy23
 * @data : 2023/8/12
 */

@RestController
@RequestMapping("/download")
@Api(tags = "文件下载控制器")
@Slf4j
@RequiredArgsConstructor
public class DownloadController {

    /**
     * 下载目标文件
     *
     * @param targetFile 目前文件
     * @param response   文件流
     */
    @GetMapping("/download")
    @ApiOperation("下载目标文件")
    public void download(@RequestParam String targetFile, HttpServletResponse response) {
        String folderPath = "E:/java/tempFile/";
        String filePath = folderPath + targetFile;

        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + targetFile + "\"");
            response.setHeader("Access-Control-Allow-Origin", "*");

            File downloadFile = new File(filePath);
            if (downloadFile.exists()) {
                FileInputStream myStream = new FileInputStream(filePath);
                IOUtils.copy(myStream, response.getOutputStream());
            }
            response.flushBuffer();
            log.info("获取文件成功！");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
