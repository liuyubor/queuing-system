package com.liuyubo.qs.db.POJO;

import lombok.Data;

import java.sql.Time;

@Data
public class Site {
    private Integer siteId;
    private String location;
    private String tel;
    private String content;
    private Integer count;
    private Time startTime;
    private Time endTime;
}
