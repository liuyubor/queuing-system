package com.liuyubo.qs.db.POJO;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Integer userID;
    private String username;
    private String password;
    private String nickname;
    private String photo;
    private String tel;
    private Object role;
    private Date createTime;
}
