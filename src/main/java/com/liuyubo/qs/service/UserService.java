package com.liuyubo.qs.service;

import com.liuyubo.qs.db.POJO.User;

import java.util.HashMap;
import java.util.Set;

public interface UserService {

    HashMap wechatLogin(String code);

    Set<String> searchUserPermissions(Integer userId);

    Integer login(HashMap param);

    Integer updatePassword(HashMap param);

    Integer update(HashMap param);

    Integer insertUser(User user);


}
