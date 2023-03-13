package com.liuyubo.qs.db.POJO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 * @TableName tb_monitor
 */
@Data
public class Monitor implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 站点id，用逗号分割
     */
    private String site;

    @Serial
    private static final long serialVersionUID = 1L;
}