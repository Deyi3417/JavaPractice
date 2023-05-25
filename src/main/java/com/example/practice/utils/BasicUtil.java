package com.example.practice.utils;

import com.example.practice.domain.request.PageSearchRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : liudy23
 * @data : 2023/5/25
 */
@Slf4j
public class BasicUtil {

    public static void defaultPageParams(PageSearchRequest request) {
        log.info("接收到的参数：{}", request);
        request.setSize(request.getSize() == 0 ? 5 : request.getSize());
    }
}
