package com.example.practice.controller;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.service.PictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author : HP
 * @date : 2023/4/14
 */
@RestController
@Slf4j
@RequestMapping("/picture")
@Api(tags = "文件控制器")
public class PictureController {

    @Resource
    private PictureService pictureService;

    @GetMapping("/watermark")
    @ApiOperation("给文件添加水印")
    public BasicResponse<?> getWatermark() throws IOException {
        String srcFile = "D:\\tmp\\activiti\\423167.jpg";
        String destFile = "D:\\tmp\\activiti\\water.jpg";
        String watermark = "liudy23";
        pictureService.addWatermarkToImage(srcFile,destFile,watermark,50,50);
        return ResultUtils.success();
    }
    @GetMapping("/watermarkResponse")
    @ApiOperation("给文件添加水印")
    public BasicResponse<?> getWatermark(HttpServletResponse response) throws IOException {
        String srcFile = "D:\\tmp\\activiti\\423167.jpg";
        String destFile = "D:\\tmp\\activiti\\water.jpg";
        String watermark = "三一技术装备有限公司 liudy23 80048349 " + DateFormatUtils.format(new Date(),"yyyy-MM-dd");
        String awtWatermark = new String(watermark.getBytes("UTF-8"));
        response.reset();
        response.setContentType("image/png");
        pictureService.addWatermarkToImage(srcFile,destFile,awtWatermark,80,100, response);
        return ResultUtils.success();
    }



}
