package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.UserDao;
import com.liuyubo.qs.db.POJO.User;
import com.liuyubo.qs.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
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
