package com.example.practice.common.ajax;

import com.example.practice.common.constant.HttpStatus;

/**
 * 返回工具类
 *
 * @author 刘德意
 * @date 2022/7/31
 */
public class ResultUtils {

    public static <T> BasicResponse<T> success(T data) {
        return new BasicResponse<>(HttpStatus.SUCCESS, "操作成功", data);
    }

    public static <T> BasicResponse<T> success(String message, T data) {
        return new BasicResponse<>(HttpStatus.SUCCESS, message, data);
    }

    public static BasicResponse error(ErrorCode errorCode) {
        return new BasicResponse(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static BasicResponse error(ErrorCode errorCode, String description) {
        return new BasicResponse(errorCode.getCode(), errorCode.getMessage(), null);
    }

    public static BasicResponse error(ErrorCode errorCode,String message, String description) {
        return new BasicResponse(errorCode.getCode(), message, null);
    }

    public static BasicResponse error(String message) {
        return new BasicResponse(HttpStatus.ERROR, message, null);
    }

    public static BasicResponse error(int code, String message) {
        return new BasicResponse(code, message, null);
    }

    public static BasicResponse error(int code, String message, String description) {
        return new BasicResponse(code, message, null, description);
    }



}
