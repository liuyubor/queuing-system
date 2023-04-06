package com.liuyubo.qs.controller;

import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.ReserveForm;
import com.liuyubo.qs.controller.form.SearchSiteByPageForm;
import com.liuyubo.qs.controller.form.searchTimeByIdForm;
import com.liuyubo.qs.service.ReservationService;
import com.liuyubo.qs.service.SiteService;
import com.liuyubo.qs.service.TimeSlotService;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/site")
@Tag(name = "SiteController", description = "核酸站点接口")
public class SiteController {

    final SiteService siteService;

    final TimeSlotService timeSlotService;

    final ReservationService reserveService;

    public SiteController(SiteService siteService, ReservationService reserveService, TimeSlotService timeSlotService) {
        this.siteService = siteService;
        this.reserveService = reserveService;
        this.timeSlotService = timeSlotService;
    }

    @GetMapping("/searchAllSite")
    @Operation(summary = "查询所有核酸站点")
    public R searchAllSite() {
        ArrayList<HashMap> list = siteService.searchAllSite();
        return R.ok().put("list", list);
    }

    @PostMapping("/searchSiteByPage")
    @Operation(summary = "分页查询核酸站点")
    public R searchSiteByPage(@Valid @RequestBody SearchSiteByPageForm form){
        int page=form.getCurrentPage();
        int size=form.getSize();
        int start=(page-1)*size;
        HashMap param= JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start",start);
        ArrayList<HashMap> sites = siteService.searchSiteByPage(param);
        return R.ok().put("sites",sites);
    }

    @PostMapping("/searchTimeById")
    @Operation(summary = "根据ID查询时段")
    public R searchTimeById(@Valid @RequestBody searchTimeByIdForm form){
        ArrayList<String> list = timeSlotService.searchTimeById(form.getId());
        return R.ok().put("list",list);
    }

    @PostMapping("/reserve")
    @Operation(summary = "预约")
    public R reserve(@Valid @RequestBody ReserveForm form){
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        Integer timeId = timeSlotService.searchTimeIdByTime(form.getTime());
        map.put("timeId",timeId);
        map.put("status","预约成功");
        int rows = reserveService.insert(map);
        return R.ok().put("rows",rows);
    }
}
