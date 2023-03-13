package com.liuyubo.qs.db.POJO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 角色表
 * @TableName tb_role
 */
@Data
public class Role implements Serializable {
    /**
     * 主键
     */
    private Object id;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 权限集合
     */
    private Object permissions;

    /**
     * 描述
     */
    private String desc;

    @Serial
    private static final long serialVersionUID = 1L;
}