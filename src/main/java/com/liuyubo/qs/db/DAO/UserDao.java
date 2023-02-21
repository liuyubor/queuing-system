package com.liuyubo.qs.db.DAO;

import com.liuyubo.qs.db.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Mapper
@Repository
public interface UserDao {

    String searchUserPermissions(Integer userId);

    Integer login(HashMap param);

    Integer UpdatePassword(HashMap param);

    Integer updateUser(HashMap param);

    Integer insert(User user);

}
