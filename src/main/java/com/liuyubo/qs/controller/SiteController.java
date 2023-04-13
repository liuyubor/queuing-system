package com.liuyubo.qs.controller;

import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.AddSiteForm;
import com.liuyubo.qs.controller.form.SearchByPageForm;
import com.liuyubo.qs.controller.form.SearchSiteInfoByIdForm;
import com.liuyubo.qs.controller.form.SearchTimeByIdForm;
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
    public R searchSiteByPage(@Valid @RequestBody SearchByPageForm form){
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
    public R searchTimeById(@Valid @RequestBody SearchTimeByIdForm form){
        ArrayList<String> list = timeSlotService.searchTimeById(form.getId());
        return R.ok().put("list",list);
    }

    @PostMapping("/searchSiteInfoById")
    @Operation(summary = "根据ID查询核酸站点信息")
    public R searchSiteInfoById(@Valid @RequestBody SearchSiteInfoByIdForm form){
        HashMap site = siteService.searchSiteInfoById(form.getId());
        return R.ok().put("site",site);
    }

    @PostMapping("/searchDequeueCountById")
    @Operation(summary = "根据ID查询核酸站点排队人数")
    public R searchDequeueCountById(@Valid @RequestBody SearchSiteInfoByIdForm form){
        String result = siteService.searchDequeueCountById(form.getId());
        HashMap map = JSONUtil.parse(result).toBean(HashMap.class);
        return R.ok().put("result",map);
    }

    @PostMapping("/allSites")
    @Operation(summary = "分页查询核酸站点")
    public R allSites(@Valid @RequestBody SearchByPageForm form){
        int page=form.getCurrentPage();
        int size=form.getSize();
        int start=(page-1)*size;
        HashMap param= JSONUtil.parse(form).toBean(HashMap.class);
        param.put("start",start);
        ArrayList<HashMap> sites = siteService.allSites(param);
        return R.ok().put("sites",sites);
    }

    @PostMapping("/addSite")
    @Operation(summary = "添加核酸站点")
    public R addSite(@Valid @RequestBody AddSiteForm form){
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        map.put("timeSlots",
                form.getTimeSlots().toString()
                        .replace("[","")
                        .replace("]",""));
        map.put("days","3");
        int rows = siteService.addSite(map);
        return R.ok().put("rows",rows);
    }
}
