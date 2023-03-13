package com.liuyubo.qs.db.POJO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 * @TableName tb_site
 */
@Data
public class Site implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 站点名称
     */
    private String name;

    /**
     * 站点描述
     */
    private String description;

    /**
     * 地理位置
     */
    private Object point;

    /**
     * 可提前几天预约
     */
    private Integer days;

    /**
     * 可预约人数
     */
    private Integer capacity;

    /**
     * 预约人数
     */
    private Integer appointmentCount;

    /**
     * 状态
     */
    private Object status;

    /**
     * 可预约时间段
     */
    private byte[] timeSlot;

    @Serial
    private static final long serialVersionUID = 1L;
}