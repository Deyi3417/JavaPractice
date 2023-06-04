package com.example.practice.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 避免重复提交注解
 *
 * @author : liudy23
 * @data : 2023/6/4
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NoRepeatSubmit {

    int interval() default 5000;

    String message() default "不允许重复提交，请稍后再试";
}
