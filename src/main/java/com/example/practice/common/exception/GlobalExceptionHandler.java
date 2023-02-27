package com.example.practice.common.exception;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ErrorCode;
import com.example.practice.common.ajax.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 全局异常处理器
 * 知识点：AOP
 *
 * @author 刘德意
 * @date 2022/7/31
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 自定义的异常
     *
     * @param e 异常信息
     * @return result
     */
    @ExceptionHandler(BusinessException.class)
    public BasicResponse businessExceptionHandler(BusinessException e) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.error(sdf.format(new Date()) + ": 自定义异常-BusinessException(" +  e.getMessage() +"),", e);
        return ResultUtils.error(e.getCode(), e.getMessage(), e.getDescription());
    }

    /**
     * 系统异常
     *
     * @param e 系统异常信息
     * @return result
     */
    @ExceptionHandler(RuntimeException.class)
    public BasicResponse businessExceptionHandler(RuntimeException e) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.error(sdf.format(new Date()) + ": 系统异常-RuntimeException(" +  e.getMessage() +"),", e);
        return ResultUtils.error(ErrorCode.SYSTEM_EXCEPTION, e.getMessage(),"系统异常");
    }
}
