package com.liuyubo.qs.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.CountForm;
import com.liuyubo.qs.controller.form.DeleteMonitorByIdsForm;
import com.liuyubo.qs.controller.form.InsertMonitorForm;
import com.liuyubo.qs.controller.form.UpdateMonitorForm;
import com.liuyubo.qs.service.MonitorService;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/monitor")
@SaCheckLogin
@Tag(name = "MonitorController", description = "视频监控模块")
public class MonitorController {

    private final MonitorService monitorService;

    public MonitorController(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    @PostMapping("/insert")
    @Operation(description = "增加监控")
    public R insert(@Valid @RequestBody InsertMonitorForm form) {
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        //TODO
        monitorService.insert(map);
        return R.ok();
    }

    @GetMapping("/selectAll")
    @Operation(description = "查询所有监控")
    public R deleteByIds() {
        //TODO
        ArrayList<HashMap> monitors = monitorService.searchAllMonitor();
        return R.ok().put("monitors",monitors);
    }

    @PostMapping("/update")
    @Operation(description = "修改监控")
    public R deleteByIds(@Valid @RequestBody UpdateMonitorForm form) {
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        //TODO
        monitorService.update(map);
        return R.ok();
    }

    @PostMapping("/delete")
    @Operation(description = "删除监控")
    public R deleteByIds(@Valid @RequestBody DeleteMonitorByIdsForm form) {
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        //TODO
        monitorService.deleteByIds(map);
        return R.ok();
    }

    @PostMapping("/count")
    @Operation(description = "获取监控人数")
    public R count(@Valid @RequestBody CountForm form) {
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        //TODO
        return R.ok();
    }

}
