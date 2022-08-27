package com.example.practice.common.ajax;

/**
 * @author 刘德意
 * @date 2022/7/31
 */
public enum ErrorCode {

    SUCCESS(200, "操作成功", ""),
    PARAM_NULL_ERROR(400, "请求参数错误", ""),
    NO_AUTH(400, "用户无权限", ""),
    NO_OBTAIN_DATA(200, "数据库没有该数据", ""),
    NO_LOGIN(400, "用户没登录", ""),
    SYSTEM_EXCEPTION(500, "系统异常", "");
    private int code;
    private String message;

    private String descripiton;

    ErrorCode(int code, String message, String descripiton) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescripiton() {
        return descripiton;
    }

    public void setDescripiton(String descripiton) {
        this.descripiton = descripiton;
    }
}
