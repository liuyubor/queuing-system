package com.liuyubo.qs.db.DAO;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;

/**
* @author kdrsi
* @description 针对表【tb_time_slot】的数据库操作Mapper
* @createDate 2023-03-13 21:40:59
* @Entity com.liuyubo.qs.db.POJO.TimeSlot
*/
@Repository
@Mapper
public interface TimeSlotMapper{

    ArrayList<String> searchTimeById(int[] times);

    String searchTimeSlotById(Integer id);

    Integer searchTimeIdByTime(String time);

    ArrayList<HashMap> getTimeSlots();
}




