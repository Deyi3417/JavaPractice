package com.example.practice.controller;

import com.alibaba.excel.EasyExcel;
import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.domain.dto.PlantUserInfo;
import com.example.practice.domain.vo.ExportDataVO;
import com.example.practice.service.ExcelHandlerService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

    @GetMapping("/importExcel")
    @ApiOperation("导入星球用户数据-一次性读出来")
    public void synchronousRead() {
            String fileName = "E:\\java\\template\\plantUserInfo.xlsx";
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 同步读取会自动finish
            List<PlantUserInfo> list = EasyExcel.read(fileName).head(PlantUserInfo.class).sheet().doReadSync();
        Gson gson = new Gson();
        for (PlantUserInfo data : list) {
                log.info("1.读取到数据:{}", gson.toJson(data));
            }
            // 这里 也可以不指定class，返回一个list，然后读取第一个sheet 同步读取会自动finish
            List<Map<Integer, String>> listMap = EasyExcel.read(fileName).sheet().doReadSync();
            for (Map<Integer, String> data : listMap) {
                // 返回每条数据的键值对 表示所在的列 和所在列的值
                log.info("2.读取到数据:{}", gson.toJson(data));
            }
    }

    @GetMapping("batchExport")
    @ApiOperation("分批次导出图片信息")
    public BasicResponse<?> batchExport(HttpServletResponse response) {
        List<ExportDataVO> data = excelHandlerService.getExcelData(1000);
        excelHandlerService.exportDataTestThread(data);
        return ResultUtils.success();
    }
}
