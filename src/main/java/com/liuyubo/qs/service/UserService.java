package com.liuyubo.qs.service;

import com.liuyubo.qs.db.POJO.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
* @author kdrsi
* @description 针对表【tb_user】的数据库操作Service
* @createDate 2023-03-13 21:40:59
*/
public interface UserService {

    HashMap wechatLogin(String code);

    Set<String> searchUserPermissions(Integer userId);

    HashMap searchUserSummary(int userId);

    Integer insert(User user);

    Integer updatePassword(HashMap param);

    Integer update(HashMap param);

    Integer searchIdByOpenId(String code);

    HashMap loadUserInfo(String openId);

    ArrayList<HashMap> allUsers(HashMap param);

    Integer deleteUser(String id);
}
