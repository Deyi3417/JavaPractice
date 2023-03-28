package com.example.practice.common.interceptor;

import com.example.practice.common.ajax.BasicResponse;
import com.example.practice.common.ajax.ResultUtils;
import com.example.practice.common.annotation.RepeatSubmit;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 防止重复提交拦截器
 *
 * @author : liudy23
 * @data : 2023/3/28
 */
@Component
@Slf4j
public abstract class RepeatSubmitInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("{}====compareParams 执行了 ==== ", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
            if (annotation != null) {
                if (this.isRepeatSubmit(request, annotation)) {
                    BasicResponse error = ResultUtils.error(annotation.message());
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    Gson gson = new Gson();
                    response.getWriter().print(gson.toJson(error));
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 验证是否重复提交由子类实现具体的防重复提交的规则
     *
     * @param request    请求对象
     * @param annotation 防复注解
     * @return 结果
     */
    public abstract boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation);
}
