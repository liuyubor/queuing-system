package com.liuyubo.qs.db.POJO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 
 * @TableName tb_time_slot
 */
@Data
public class TimeSlot implements Serializable {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 时间段
     */
    private byte[] timeSlot;

    @Serial
    private static final long serialVersionUID = 1L;
}