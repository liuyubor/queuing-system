package com.liuyubo.qs.service;

import java.util.ArrayList;

/**
* @author kdrsi
* @description 针对表【tb_time_slot】的数据库操作Service
* @createDate 2023-03-13 21:40:59
*/
public interface TimeSlotService {

    ArrayList<String> searchTimeById(Integer id);

    Integer searchTimeIdByTime(String time);
}
