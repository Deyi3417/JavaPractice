package com.example.practice.common.interceptor.impl;

import com.alibaba.excel.util.DateUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.practice.common.annotation.RepeatSubmit;
import com.example.practice.common.interceptor.RepeatSubmitInterceptor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 判断请求url和数据是否和上一次相同，
 * 如果和上次相同，则是重复提交表单。 有效时间为10秒内。
 *
 * @author : liudy23
 * @data : 2023/3/28
 */
@Slf4j
@Component
public class SameUrlDataInterceptor extends RepeatSubmitInterceptor {

    public final String REPEAT_PARAMS = "repeatParams";

    public final String REPEAT_TIME = "repeatTime";

    public final String SESSION_REPEAT_KEY = "repeatData";

    public final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";


    @SuppressWarnings("unchecked")
    @Override
    public boolean isRepeatSubmit(HttpServletRequest request, RepeatSubmit annotation) {

//        log.info("{}==== SameUrlDataInterceptor.java 执行了 ==== ", DateFormatUtils.format(new Date(), DEFAULT_DATE_FORMAT));
        Gson gson = new Gson();
        // 本次参数及系统时间
        String nowParams = gson.toJson(request.getParameterMap());
        HashMap<String, Object> nowDataMap = new HashMap<>();
        nowDataMap.put(REPEAT_PARAMS, nowParams);
        nowDataMap.put(REPEAT_TIME, System.currentTimeMillis());

        // 请求地址（作为存放session的key值）
        String url = request.getRequestURI();

        HttpSession session = request.getSession();
        Object sessionObj = session.getAttribute(SESSION_REPEAT_KEY);
        if (sessionObj != null) {
            Map<String, Object> sessionMap = JSON.parseObject(JSON.toJSONString(sessionObj), Map.class);
            if (sessionMap.containsKey(url)) {
                Object urlObject = sessionMap.get(url);
                Map<String, Object> preDataMap = JSON.parseObject(JSON.toJSONString(urlObject), Map.class);
                if (compareParams(nowDataMap, preDataMap) && compareTime(nowDataMap, preDataMap, annotation.interval())) {
                    return true;
                }
            }
        }
        Map<String, Object> sessionMap = new HashMap<>();
        sessionMap.put(url, nowDataMap);
        session.setAttribute(SESSION_REPEAT_KEY, sessionMap);
        return false;
    }

    /**
     * 判断参数是否相同
     */
    private boolean compareParams(Map<String, Object> nowMap, Map<String, Object> preMap) {
        log.info("{}====compareParams 执行了 ==== ", DateFormatUtils.format(new Date(), DEFAULT_DATE_FORMAT));
        String nowParams = (String) nowMap.get(REPEAT_PARAMS);
        String preParams = (String) preMap.get(REPEAT_PARAMS);
        return nowParams.equals(preParams);
    }

    /**
     * 判断两次间隔时间
     */
    private boolean compareTime(Map<String, Object> nowMap, Map<String, Object> preMap, int interval) {
        log.info("{}====compareTime 执行了 ===={}--{} ", DateFormatUtils.format(new Date(), DEFAULT_DATE_FORMAT), nowMap,preMap);
        long time1 = (Long) nowMap.get(REPEAT_TIME);
        long time2 = (Long) preMap.get(REPEAT_TIME);
        return (time1 - time2) < interval;
    }
}
