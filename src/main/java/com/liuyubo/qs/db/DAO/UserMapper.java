package com.liuyubo.qs.db.DAO;

import com.liuyubo.qs.db.POJO.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Set;

/**
* @author kdrsi
* @description 针对表【tb_user】的数据库操作Mapper
* @createDate 2023-03-13 21:40:59
* @Entity com.liuyubo.qs.db.POJO.User
*/
@Repository
@Mapper
public interface UserMapper {

    Set<String> searchUserPermissions(Integer userId);

    HashMap searchUserSummary(int userId);

    Integer insert(User user);

    Integer updatePassword(HashMap param);

    Integer update(HashMap param);
}




