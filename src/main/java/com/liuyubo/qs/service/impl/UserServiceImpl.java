package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.UserMapper;
import com.liuyubo.qs.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

/**
* @author kdrsi
* @description 针对表【tb_user】的数据库操作Service实现
* @createDate 2023-03-13 21:40:59
*/
@Service
public class UserServiceImpl implements UserService {


    private final RedisTemplate redisTemplate;
    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper, RedisTemplate redisTemplate) {
        this.userMapper = userMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public HashMap wechatLogin(String code) {
        HashMap map = new HashMap();
        boolean result = false;
        if (Boolean.TRUE.equals(redisTemplate.hasKey(code))) {
            String value = redisTemplate.opsForValue().get(code).toString();
            if (!"false".equals(value)) {
                result = true;
                redisTemplate.delete(code);
                int userId = Integer.parseInt(value);
                map.put("userId", userId);
            }
        }
        map.put("result", result);
        return map;
    }

    @Override
    public Set<String> searchUserPermissions(Integer userId) {
        return userMapper.searchUserPermissions(userId);
    }

    @Override
    public HashMap searchUserSummary(int userId) {
        return userMapper.searchUserSummary(userId);
    }


}




