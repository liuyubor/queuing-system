package com.liuyubo.qs.db.DAO;

import com.liuyubo.qs.db.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Set;

@Mapper
@Repository
public interface UserDao {

    Set<String> searchUserPermissions(int userId);

    Integer login(HashMap param);

    Integer UpdatePassword(HashMap param);

    Integer updateUser(HashMap param);

    Integer insert(User user);


}
