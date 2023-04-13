package com.liuyubo.qs.controller;

import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.DeleteForm;
import com.liuyubo.qs.controller.form.ReserveForm;
import com.liuyubo.qs.controller.form.SearchByPageForm;
import com.liuyubo.qs.controller.form.UpdateReserveForm;
import com.liuyubo.qs.service.ReservationService;
import com.liuyubo.qs.service.SiteService;
import com.liuyubo.qs.service.TimeSlotService;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/reserve")
@Tag(name = "ReserveController", description = "预约接口")
public class ReserveController {

    final
    TimeSlotService timeSlotService;

    final
    ReservationService reserveService;

    final
    SiteService siteService;

    public ReserveController(TimeSlotService timeSlotService, ReservationService reserveService, SiteService siteService) {
        this.timeSlotService = timeSlotService;
        this.reserveService = reserveService;
        this.siteService = siteService;
    }

    @PostMapping("/reserve")
    @Operation(summary = "预约")
    public R reserve(@Valid @RequestBody ReserveForm form){
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        Integer timeId = timeSlotService.searchTimeIdByTime(form.getTime());
        map.put("timeId",timeId);
        map.put("status","预约成功");
        if (reserveService.selectTimeConflict(map)) {
            return R.ok("该时间段已被预约");
        }
        int rows1 = siteService.updateSiteCount(Integer.valueOf(form.getSiteId()));
        int rows2 = reserveService.insert(map);
        return R.ok().put("updateSiteCount",rows1).put("rows",rows2);
    }

    @PostMapping("/allreserves")
    @Operation(summary = "分页查询预约记录")
    public R allReserves(@Valid @RequestBody SearchByPageForm form){
        int page=form.getCurrentPage();
        int size=form.getSize();
        int start=(page-1)*size;
        HashMap param= JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start",start);
        ArrayList<HashMap> reserves = reserveService.allReserves(param);
        return R.ok().put("reserves",reserves);
    }

    @PostMapping("/deleteReserve")
    @Operation(summary = "删除预约记录")
    public R deleteReserve(@Valid @RequestBody DeleteForm form){
        int rows = reserveService.deleteReserve(form.getId());
        return R.ok().put("rows",rows);
    }

    @PostMapping("/updateReserve")
    @Operation(summary = "更新预约记录")
    public R updateReserve(@Valid @RequestBody UpdateReserveForm form){
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        int rows = reserveService.updateReserve(map);
        return R.ok().put("rows",rows);
    }
}
