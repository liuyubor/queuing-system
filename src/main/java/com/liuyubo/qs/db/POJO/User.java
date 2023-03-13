package com.liuyubo.qs.db.POJO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tb_user
 */
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 姓名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 头像网址
     */
    private String photo;

    /**
     * 电话号码
     */
    private String tel;

    /**
     * 角色
     */
    private Object role;

    /**
     * 是否是管理员
     */
    private Integer root;

    /**
     * 创建时间
     */
    private Date creatTime;

    @Serial
    private static final long serialVersionUID = 1L;
}