package com.example.practice.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.alibaba.fastjson.JSON;
import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.common.mapstruct.basic.UserConvert;
import com.example.practice.domain.User;
import com.example.practice.domain.dto.ImportDeviceData;
import com.example.practice.domain.dto.PlantUserInfo;
import com.example.practice.domain.vo.ExportDataVO;
import com.example.practice.domain.vo.ExportUserVO;
import com.example.practice.service.ExcelHandlerService;
import com.example.practice.service.UserService;
import com.example.practice.utils.DateUtil;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @author : liudy23
 * @data : 2023/3/5
 */
@RestController
@Slf4j
@RequestMapping("/test")
@Api(tags = "处理excel控制器")
public class ExcelHandlerController {

    @Resource
    private ExcelHandlerService excelHandlerService;

    @Autowired
    private UserService userService;

    @Resource
    private UserConvert userConvert;

    @GetMapping("/importExcel")
    @ApiOperation("导入星球用户数据-一次性读出来")
    public BasicResponse synchronousRead() {
        String fileName = "D:\\tmp\\tempfile\\plantUserInfo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<PlantUserInfo> list = EasyExcel.read(fileName).head(PlantUserInfo.class).sheet().doReadSync();
        Gson gson = new Gson();
        for (PlantUserInfo data : list) {
            log.info("1.读取到数据:{}", gson.toJson(data));
            log.info("星球编号：{},用户昵称：{}", data.getPlanetCode(), data.getUsername());
        }
        // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
        List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            log.info("2.读取到数据:{}", gson.toJson(data));
        }
        return ResultUtils.success();
    }


    @GetMapping("/read/device")
    @ApiOperation("导入实验设备数据-一次性读出来")
    public BasicResponse synchronousReadDeviceData() {
        String fileName = "D:\\tmp\\tempfile\\实验设备批量导入.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
        List<ImportDeviceData> list = EasyExcel.read(fileName).head(ImportDeviceData.class).sheet().doReadSync();
        Gson gson = new Gson();
        for (ImportDeviceData data : list) {
            log.info("1.读取到数据:{}", gson.toJson(data));
        }
        // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
        List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
        for (Map<Integer, String> data : listMap) {
            // 返回每条数据的键值对 表示所在的列 和所在列的值
            log.info("2.读取到数据:{}", gson.toJson(data));
        }
        return ResultUtils.success(JSON.toJSONString(list));
    }

    @PostMapping("/device/upload")
    @ApiOperation("从前端页面上传excel文件")
    public BasicResponse<?> uploadExcel(@RequestParam("file") MultipartFile file) throws IOException {

//        EasyExcel.read(file.getInputStream(), Device.class, new DeviceListener(devices)).sheet().doRead();
        List<ImportDeviceData> importDeviceData = EasyExcel.read(file.getInputStream()).head(ImportDeviceData.class).sheet().doReadSync();
        Gson gson = new Gson();
        for (ImportDeviceData data : importDeviceData) {
            log.info("1.读取到数据:{}", gson.toJson(data));
        }
        return ResultUtils.success();
    }

    @GetMapping("batchExport")
    @ApiOperation("分批次导出图片信息")
    public BasicResponse<?> batchExport(HttpServletResponse response) {
        List<ExportDataVO> data = excelHandlerService.getExcelData(1000);
        excelHandlerService.exportDataTestThread(data);
        return ResultUtils.success();
    }

    @GetMapping("/userExport")
    @ApiOperation("导出用户数据")
    public void exportUser(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = "liudy23-" + DateUtil.getTimeString();
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        OutputStream os = response.getOutputStream();

        List<User> userList = userService.list();
        List<ExportUserVO> exportUserList = userConvert.toExpotrUserList(userList);
        for (ExportUserVO vo : exportUserList) {
            vo.setProfile("DJSFKLSADJFKLJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJJ,DEYI IS SO HANSOME, 可以吗？");
            System.out.println("--------" + vo.getCreateTime());
        }

        // 创建样式策略，居中对齐
        WriteCellStyle style = new WriteCellStyle();
        style.setWrapped(true);
        style.setHorizontalAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        WriteCellStyle headStrategy = new WriteCellStyle();
        headStrategy.setWrapped(true);
        headStrategy.setHorizontalAlignment(HorizontalAlignment.CENTER);
        headStrategy.setVerticalAlignment(VerticalAlignment.CENTER);
        HorizontalCellStyleStrategy strategy = new HorizontalCellStyleStrategy(headStrategy, style);

        EasyExcel.write(os, ExportUserVO.class).registerWriteHandler(strategy).sheet("用户").doWrite(exportUserList);
        log.info("导出数据成功：{}", exportUserList);
    }
}
