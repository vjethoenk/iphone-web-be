package com.example.iphone_web_be.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String key,
                     Object value,
                     long timeout,
                     TimeUnit unit){

        redisTemplate.opsForValue()
                .set(key,value,timeout,unit);
    }

    public Object get(String key){

        return redisTemplate
                .opsForValue()
                .get(key);
    }

    public void delete(String key){

        redisTemplate.delete(key);
    }

    public boolean exists(String key){

        return Boolean.TRUE.equals(
                redisTemplate.hasKey(key)
        );
    }

    public Long getExpire(String key){

        return redisTemplate.getExpire(
                key,
                TimeUnit.SECONDS
        );
    }

}
