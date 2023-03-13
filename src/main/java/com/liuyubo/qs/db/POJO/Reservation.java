package com.liuyubo.qs.db.POJO;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName tb_reservation
 */
@Data
public class Reservation implements Serializable {
    /**
     * 
     */
    private Integer id;

    /**
     * 
     */
    private Integer user;

    /**
     * 
     */
    private Integer site;

    /**
     * 
     */
    private Date date;

    /**
     * 
     */
    private Integer timeSlot;

    /**
     * 
     */
    private Object status;

    @Serial
    private static final long serialVersionUID = 1L;
}