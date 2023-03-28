package com.example.practice.common.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解防止表单重复提交
 *
 * @author : liudy23
 * @data : 2023/3/28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    public int interval() default 5000;

    public String message() default "不允许重复提交，请稍后再试";
}
