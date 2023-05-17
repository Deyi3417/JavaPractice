package com.example.practice.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.DateUtils;
import com.alibaba.excel.util.FileUtils;
import com.alibaba.fastjson.JSON;
import com.example.practice.common.ajax.AjaxResult;
import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ErrorCode;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.common.annotation.RepeatSubmit;
import com.example.practice.common.config.properties.BasicProperties;
import com.example.practice.common.exception.BusinessException;
import com.example.practice.common.mapstruct.basic.UserConvert;
import com.example.practice.domain.Tag;
import com.example.practice.domain.User;
import com.example.practice.domain.request.ExtendParams;
import com.example.practice.domain.vo.ExportUserVO;
import com.example.practice.domain.vo.ImageDemoData;
import com.example.practice.domain.vo.RequestTestVO;
import com.example.practice.domain.vo.SafetyUser;
import com.example.practice.service.FileHandlerService;
import com.example.practice.service.TagService;
import com.example.practice.service.UserService;
import com.example.practice.utils.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 刘德意
 * @data 2022-07-30
 */
@RestController
@Slf4j
@RequestMapping("/test")
@Api(tags = "TestController 测试")
public class TestController {

    public static final String PDF_FILE_1 = "D:\\tmp\\usercenter\\saveFile\\2023-03-17\\liudy23.pdf";
    public static final String PDF_FILE_2 = "D:\\tmp\\usercenter\\saveFile\\2023-03-17\\liudy23.docx";

    @Autowired
    private UserService userService;

    @Resource
    private FileHandlerService fileHandlerService;

    @Resource
    private TagService tagService;

    @Resource
    private BasicProperties basicProperties;

    @GetMapping("/fileToImg")
    @ApiOperation("测试文件转图片进行展示")
    private void fileToImg(HttpServletResponse response, @RequestParam("filePath") String filePath) {
        File file = new File(filePath);
        String fileType = filePath.substring(filePath.lastIndexOf(".") + 1);
        fileHandlerService.fileToImg(file, response, fileType);
    }

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

    /**
     * https://easyexcel.opensource.alibaba.com/docs/current/quickstart/read
     *
     * @param response
     * @throws IOException
     */
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

    @GetMapping("/getById")
    @RepeatSubmit
    @ApiOperation("测试自定义重复提交注解")
    public BasicResponse<SafetyUser> getUserById(@RequestParam Long id) {
        User user = userService.getById(id);
        return ResultUtils.success(UserConvert.INSTANCE.toSafetyUser(user));
    }

    @PostMapping("/date")
    @ApiOperation("测试时间格式转化")
    public BasicResponse<?> dateFormat(@RequestBody RequestTestVO request) {

        log.info("传入的参数：{}====={}", request.getId(), request.getExpectedEndTime());
        User user = userService.getById(request.getId());
        user.setUpdateTime(request.getExpectedEndTime());
        userService.updateById(user);
        return ResultUtils.success(user);
    }

    @PostMapping("/paramsExtends")
    @ApiOperation("参数参数学习")
    public BasicResponse<?> extendsParams(@RequestBody ExtendParams params) {
        log.info("请求参数：{}", JSON.toJSONString(params));
        return ResultUtils.success(JSON.toJSONString(params));
    }


    @GetMapping("/exportPicture")
    @ApiOperation("测试导出包含图片")
    public void exportPicture() throws Exception {
        String format = DateUtils.format(new Date(), DateUtils.DATE_FORMAT_14);
        String fileName = "D:\\tmp\\activiti\\test_export_picture_" + format + ".xlsx";

        // 设置响应头信息
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        response.setHeader("Content-disposition", "attachment;filename=" + fileName);

        // 创建图片文件并读取图片
        String imagePath = "D:\\tmp\\activiti\\liudy23.jpg";
        try (InputStream inputStream = FileUtils.openInputStream(new File(imagePath))) {
            List<ImageDemoData> list = new ArrayList<>();
            ImageDemoData imageDemoData = new ImageDemoData();
            imageDemoData.setFile(new File(imagePath));
            imageDemoData.setId(1);
            imageDemoData.setUserName("刘德意--");
            imageDemoData.setTime(new Date());
            list.add(imageDemoData);

//            WriteCellData<Object> writeCellData = new WriteCellData<>();
//            // 放入了文字
//            writeCellData.setType(CellDataTypeEnum.STRING);
//            writeCellData.setStringValue("liudy23 is so handsome");
//            imageDemoData.setWriteCellDataFile(writeCellData);
//
//            // 可以放入多个图片
//            List<ImageData> imageDataList = new ArrayList<>();
//            ImageData imageData = new ImageData();
//            imageDataList.add(imageData);
//            writeCellData.setImageDataList(imageDataList);
//            // 放入2进制图片
//            imageData.setImage(FileUtils.readFileToByteArray(new File(imagePath)));
//            // 图片类型
//            imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_PNG);
//            // 上 右 下 左 需要留空
//            // 这个类似于 css 的 margin
//            // 这里实测 不能设置太大 超过单元格原始大小后 打开会提示修复。暂时未找到很好的解法。
//            imageData.setTop(5);
//            imageData.setRight(0);
//            imageData.setBottom(5);
//            imageData.setLeft(0);

//            // 放入第二个图片
//            imageData = new ImageData();
//            imageDataList.add(imageData);
//            writeCellData.setImageDataList(imageDataList);
//            imageData.setImage(FileUtils.readFileToByteArray(new File(imagePath)));
//            imageData.setImageType(ImageData.ImageType.PICTURE_TYPE_PNG);
//            imageData.setTop(5);
//            imageData.setRight(0);
//            imageData.setBottom(5);
//            imageData.setLeft(0);
////            // 设置图片的位置 假设 现在目标 是 覆盖 当前单元格 和当前单元格右边的单元格
//            // 起点相对于当前单元格为0 当然可以不写
//            imageData.setRelativeFirstRowIndex(0);
//            imageData.setRelativeFirstColumnIndex(1);
//            imageData.setRelativeLastRowIndex(0);
////            // 前面3个可以不写  下面这个需要写 也就是 结尾 需要相对当前单元格 往右移动一格
////            // 也就是说 这个图片会覆盖当前单元格和 后面的那一格
//            imageData.setRelativeLastColumnIndex(1);

            // 写入数据
            EasyExcel.write(fileName, ImageDemoData.class).sheet().doWrite(list);
        }
    }

    @PostMapping("borrow")
    @ApiOperation("设备借用")
    public BasicResponse<?> borrowDevices(@RequestBody List<Integer> ids) {
        log.info("接收参数：{}", JSON.toJSONString(ids));
        System.out.println("接收参数： " + JSON.toJSONString(ids));
//        QueryWrapper<User> query = new QueryWrapper<>();
//        query.in("id",ids).eq("is_delete",0);
        List<User> users = userService.listByIds(ids);
        return ResultUtils.success(users);
    }

    @PostMapping("/insert")
    @ApiOperation("插入标签")
    public BasicResponse<?> insetUser(@RequestBody Tag tag) {
        // 插进数据库之后，就会生成主键id
        boolean save = tagService.saveOrUpdate(tag);
        if (save) {
            log.info("展示标签：" + tag);
            return ResultUtils.success();
        }
        return ResultUtils.error("插入失败");
    }

    @GetMapping("/isIpWhite")
    @ApiOperation("ip白名单测试")
    public BasicResponse<?> isIpWhite(@RequestParam(value = "ip") String ip) {
        Set<String> ipList = FileUtil.loadIpWhiteList(basicProperties.getFileIpWhiteFile());
        if (!ipList.contains(ip)) {
            return ResultUtils.error("您不在IP白名单，无法获取资源");
        }
        System.out.println(ipList);
        log.info("ip白名单：{}", ipList);
        return ResultUtils.success(ipList);
    }

    @GetMapping("/redirect")
    @ApiOperation("重定向测试")
    public BasicResponse<List<User>> redirect(HttpServletResponse response, Long userId) throws IOException {
        // 需要注意的是，使用 response.sendRedirect() 方法时，必须确保在调用该方法之前，不要向客户端发送任何数据，否则会抛出 IllegalStateException 异常。
        User user = userService.getById(userId);
        user.setUsername("北暮城南");
        userService.updateById(user);
        response.sendRedirect("http://localhost:9080/api/test/target");
        List<User> userList = userService.getUserList();
        return ResultUtils.success(userList);
    }

    @GetMapping("/target")
    @ApiOperation("重定向目标地址-获取数据")
    public BasicResponse<List<User>> redirect() {
        List<User> userList = userService.getUserList();
        return ResultUtils.success(userList);
    }

}
