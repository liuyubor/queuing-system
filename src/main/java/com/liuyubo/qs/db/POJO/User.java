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
    private String name;
    private Object sex;
    private String tel;
    private String email;
    private Object role;
    private Boolean root;
    private Date createTime;
}
