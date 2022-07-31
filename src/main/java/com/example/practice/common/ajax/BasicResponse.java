package com.example.practice.common.ajax;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 刘德意
 * @date 2022/7/31
 */
@Data
public class BasicResponse<T> implements Serializable {

    private static final long serialVersionUID = 3544772168954420548L;

    private int code;

    private String message;

    private T data;

    private String description;

    public BasicResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public BasicResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BasicResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public BasicResponse(ErrorCode errorCode) {
        this(errorCode.getCode(),errorCode.getMessage(),null);
    }

    public BasicResponse(ErrorCode errorCode, String description) {
        this(errorCode.getCode(),errorCode.getMessage(),null);
    }

    public BasicResponse(int code, String message, T data, String description) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.description = description;
    }
}
