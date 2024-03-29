package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.UserMapper;
import com.liuyubo.qs.db.POJO.User;
import com.liuyubo.qs.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
* @author kdrsi
* @description 针对表【tb_user】的数据库操作Service实现
* @createDate 2023-03-13 21:40:59
*/
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public HashMap wechatLogin(String code) {
        return null;
    }

    @Override
    public Set<String> searchUserPermissions(Integer userId) {
        return userMapper.searchUserPermissions(userId);
    }

    @Override
    public HashMap searchUserSummary(int userId) {
        return userMapper.searchUserSummary(userId);
    }

    @Override
    public Integer insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public Integer updatePassword(HashMap param) {
        return userMapper.updatePassword(param);
    }

    @Override
    public Integer update(HashMap param) {
        return userMapper.update(param);
    }

    @Override
    public Integer searchIdByOpenId(String code) {
        return userMapper.searchIdByOpenId(code);
    }

    @Override
    public HashMap loadUserInfo(String openId) {
        return userMapper.loadUserInfo(openId);
    }

    @Override
    public ArrayList<HashMap> allUsers(HashMap param) {
        return userMapper.allUsers(param);
    }

    @Override
    public Integer deleteUser(String id) {
        return userMapper.deleteUser(id);
    }

    @Override
    public Integer updateUser(HashMap param) {
        return userMapper.updateUser(param);
    }


}




