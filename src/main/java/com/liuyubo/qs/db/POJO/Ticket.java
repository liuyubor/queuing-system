package com.liuyubo.qs.db.POJO;

import lombok.Data;

import java.util.Date;

@Data
public class Ticket {

    private Integer id;
    private Integer userId;
    private String number;
    private Byte typeId;
    private Byte status;
    private Date createTime;
}
