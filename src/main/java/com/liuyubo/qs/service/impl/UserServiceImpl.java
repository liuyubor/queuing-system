package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.UserDao;
import com.liuyubo.qs.db.POJO.User;
import com.liuyubo.qs.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {


    private final RedisTemplate redisTemplate;
    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao, RedisTemplate redisTemplate) {
        this.userDao = userDao;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public HashMap wechatLogin(String code) {
        HashMap map = new HashMap();
        boolean result = false;
        if (redisTemplate.hasKey(code)) {
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
        return userDao.searchUserPermissions(userId);
    }

    @Override
    public Integer login(HashMap param) {
        return userDao.login(param);
    }

    @Override
    public Integer updatePassword(HashMap param) {
        return userDao.UpdatePassword(param);
    }

    @Override
    public Integer update(HashMap param) {
        return userDao.updateUser(param);
    }

    @Override
    public Integer insertUser(User user) {
        return userDao.insert(user);
    }
}
