package com.liuyubo.qs.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.InsertMonitorForm;
import com.liuyubo.qs.service.MonitorService;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/insert")
    @Operation(description = "增加监控")
    public R deleteByIds(@Valid @RequestBody InsertMonitorForm form) {
        HashMap map = JSONUtil.parse(form).toBean(HashMap.class);
        //TODO
        monitorService.insert(map);
        return R.ok();
    }
}
