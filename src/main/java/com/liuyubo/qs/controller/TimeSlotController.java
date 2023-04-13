package com.liuyubo.qs.controller;

import com.liuyubo.qs.service.TimeSlotService;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/timeslot")
@Tag(name = "时间段管理", description = "时间段管理")
public class TimeSlotController {

    final
    TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @PostMapping("/getTimeSlots")
    @Schema(description = "获取时间段列表")
    public R getTimeSlots() {
        ArrayList<HashMap> timeSlots = timeSlotService.getTimeSlots();
        return R.ok().put("timeSlots", timeSlots);
    }
}
