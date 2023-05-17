package com.example.practice.utils;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Redis工具类
 *
 * @author : HP
 * @date : 2023/5/11
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;


    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void setHashValue(String hashKey, String key, Object value) {
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        hashOps.put(hashKey, key, value);
    }

    public Object getHashValue(String hashKey, String key) {
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        return hashOps.get(hashKey, key);
    }

    public void deleteHashValue(String hashKey, String key) {
        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        hashOps.delete(hashKey, key);
    }


}
