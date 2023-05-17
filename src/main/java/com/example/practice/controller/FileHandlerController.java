package com.example.practice.controller;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.common.config.properties.BasicProperties;
import com.example.practice.domain.request.DeviceInfo;
import com.example.practice.service.FileHandlerService;
import com.example.practice.service.PictureService;
import com.example.practice.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author : HP
 * @date : 2023/4/14
 */
@RestController
@Slf4j
@RequestMapping("/picture")
@Api(tags = "文件处理控制器")
public class FileHandlerController {

    @Resource
    private PictureService pictureService;

    @Resource
    private FileHandlerService fileHandlerService;

    @Resource
    private BasicProperties basicProperties;

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

    @GetMapping("/wordToPdf")
    @ApiOperation("测试word转pdf")
    private void wordToPDF(HttpServletResponse response, @RequestParam("filePath") String filePath) {
        log.info("====参数===={}", basicProperties.getFileTempPdf());
        File file = new File(filePath);
        FileUtil.wordToPdf(file);
    }

    @GetMapping("/asposeWordToPDF")
    @ApiOperation("测试word转pdf-aspose")
    private void asposeWordToPDF(HttpServletResponse response, @RequestParam("filePath") String filePath) {
        File file = new File(filePath);
        FileUtil.asposeWordToPDF(file);
    }

    @GetMapping("fileToImg")
    @ApiOperation("测试文件转图片进行展示")
    private void fileToImg(HttpServletResponse response, @RequestParam("filePath") String filePath) {
        File file = new File(filePath);
        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
        fileHandlerService.fileToImg(file, response, fileType);
    }



    @GetMapping("/qrCode")
    @ApiOperation("生成二维码在前端页面显示")
    public void qrCode(HttpServletResponse response) {
        DeviceInfo info = new DeviceInfo();
        info.setDeviceName("iphone12");
        info.setDeviceModel("128G");
        info.setProductionDate("2021-03-16");
        info.setSerialNumber("2023042022222");
        String deviceName = "iphone12";
        String deviceModel = "128G";
        String serialNumber = "Everything will goes well";
        String productionDate = "2021-03-16";
        String qrCodeContent = "设备名称：" + deviceName + "\n设备型号：" + deviceModel + "\n序列号：" + serialNumber + "\n生产日期：" + productionDate;
        pictureService.generateQRCodeImage(qrCodeContent.toString(), 150,150, response);
    }



}
