package com.example.practice.common.aspect;

import com.example.practice.common.annotation.NoRepeatSubmit;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author : liudy23
 * @data : 2023/6/4
 */
@Aspect
@Component
@Slf4j
public class NoRepeatSubmitAspect {

    /**
     * 缓存map
     */
    private Map<String, Long> CACHE_MAP = new HashMap<>();

    @Around("@annotation(noRepeatSubmit)")
    public Object checkRepeatSubmit(ProceedingJoinPoint joinPoint, NoRepeatSubmit noRepeatSubmit) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String requestUrl = request.getRequestURI();
        System.out.println("==requestUrl== " + requestUrl);
        String id = request.getSession().getId();
        System.out.println("==id== " + id);
        String requestKey = generateRequestKey(joinPoint);
        // 后期只要把这个requestKey调整成唯一即可，每个用户对应的key不同，根据用户来设置key
        log.info("requestKey: {}", requestKey);
        long interval = noRepeatSubmit.interval();
        long currentTime = System.currentTimeMillis();
        if (CACHE_MAP.containsKey(requestKey)) {
            long previousTime = CACHE_MAP.get(requestKey);
            if (currentTime - previousTime < interval) {
                log.info("请求链接（{}）重复提交：之前的时间：{}", requestUrl, previousTime);
                System.out.println("重复请求：当前系统的时间：" + currentTime);
                System.out.println("重复请求：之前的时间：" + previousTime);
                throw new RuntimeException(noRepeatSubmit.message());
            }
        }
        CACHE_MAP.put(requestKey, currentTime);
        return joinPoint.proceed();
    }

    private String generateRequestKey(ProceedingJoinPoint joinPoint) {
        return joinPoint.getSignature().toLongString();
    }
}
