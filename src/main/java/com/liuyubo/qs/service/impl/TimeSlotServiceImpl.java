package com.liuyubo.qs.service.impl;

import com.liuyubo.qs.db.DAO.TimeSlotMapper;
import com.liuyubo.qs.service.TimeSlotService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
* @author kdrsi
* @description 针对表【tb_time_slot】的数据库操作Service实现
* @createDate 2023-03-13 21:40:59
*/
@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    final
    TimeSlotMapper timeMapper;

    public TimeSlotServiceImpl(TimeSlotMapper timeMapper) {
        this.timeMapper = timeMapper;
    }


    @Override
    public ArrayList<String> searchTimeById(Integer id) {
        String timeSlot = timeMapper.searchTimeSlotById(id);
        String[] times = timeSlot.split(",");
        int[] intArray = Arrays.stream(times)
                .map(Integer::parseInt)
                .mapToInt(Integer::intValue)
                .toArray();
        return timeMapper.searchTimeById(intArray);
    }

    @Override
    public Integer searchTimeIdByTime(String time) {
        return timeMapper.searchTimeIdByTime(time);
    }

    @Override
    public ArrayList<HashMap> getTimeSlots() {
        return timeMapper.getTimeSlots();
    }
}




