package com.example.practice.common.aspect;

import com.example.practice.utils.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 切面类实现IP地址校验逻辑
 *
 * @author : HP
 * @date : 2023/5/22
 */
@Aspect
@Component
@Slf4j
@Order(2)
public class WhitelistedIPAspect {

    private static final List<String> IP_WHITELIST = Arrays.asList("127.0.0.1", "192.168.0.1","0:0:0:0:0:0:0:1");

    @Pointcut(value = "@annotation(com.example.practice.common.annotation.WhitelistedIP)")
    private void whiteListedIPPointCut() {

    }

    @Around("whiteListedIPPointCut()")
    public Object validateIP(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String clientIP = getClientIP(request);

        if (!isWhitelistedIP(clientIP)) {
            throw new RuntimeException("外网不让其进行操作");
        }
        return joinPoint.proceed();
    }

    private String getClientIP(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    private boolean isWhitelistedIP(String ipAddress) {
        return IP_WHITELIST.contains(ipAddress);
    }



}
