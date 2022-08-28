package com.example.practice.common.exception;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ErrorCode;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.common.constant.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    /**
     * 定义参数校验异常
     *
     * @param e 系统异常信息
     * @return result
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BasicResponse businessExceptionHandler(MethodArgumentNotValidException e) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            if (fieldErrors.size() > 0) {
                FieldError fieldError = fieldErrors.get(0);
                log.error(sdf.format(new Date()) + ": 参数校验异常-MethodArgumentNotValidException(" +  fieldError.getDefaultMessage() +"),", e);
                return ResultUtils.error(HttpStatus.BAD_REQUEST, fieldError.getDefaultMessage(), fieldError.getCode());
            }
        }
        return ResultUtils.error(ErrorCode.SYSTEM_EXCEPTION, e.getMessage(),"系统异常");
    }

}
