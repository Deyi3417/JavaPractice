package com.example.practice.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.example.practice.common.ajax.AjaxResult;
import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ErrorCode;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.common.exception.BusinessException;
import com.example.practice.domain.User;
import com.example.practice.domain.vo.ExportUserVO;
import com.example.practice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.beans.SimpleBeanInfo;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 刘德意
 * @data 2022-07-30
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserService userService;


    @GetMapping("/getByIds")
    public AjaxResult getUserByIds(Integer[] ids) {
        log.info("根据ids获取用户执行了");
        if (ids.length == 0) {
            throw new BusinessException(ErrorCode.PARAM_NULL_ERROR, "哪个参数的异常，description给前端使用");
        }
        List<User> users = userService.getUserByIds(ids);
        if (users.size() > 0) {
            return AjaxResult.success(users);
        } else {
            return AjaxResult.error(200, "获取用户消息失败");
        }
    }

    @GetMapping("/test02")
    public BasicResponse<?> test02(Integer[] ids) {
        log.info("根据ids获取用户执行了");
        System.out.println("liudy23测试上传代码");
        List<User> users = userService.getUserByIds(ids);
        if (users.size() > 0) {
            return ResultUtils.success(users);
        } else {
            return ResultUtils.error(ErrorCode.NO_OBTAIN_DATA);
        }
    }

    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        log.info("Start exporting excel");
        List<ExportUserVO> userList = userService.getExportUserVO();
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
        String format = sdf.format(new Date());
        String fileName = URLEncoder.encode("用户表-" + format, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        ServletOutputStream os = response.getOutputStream();
        EasyExcel.write(os).withTemplate("E:/java/template/exportExcelTemplate.xlsx").sheet().doFill(userList);
        log.info("End exporting excel");
    }

    @GetMapping("/list")
    public BasicResponse<List<User>> list() {
        List<User> userList = userService.list();
        return ResultUtils.success(userList);
    }
}
