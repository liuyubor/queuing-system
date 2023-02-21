package com.liuyubo.qs.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.InsertTicketForm;
import com.liuyubo.qs.controller.form.SearchTicketByIdForm;
import com.liuyubo.qs.controller.form.SearchTicketCountForm;
import com.liuyubo.qs.service.TicketService;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
@Tag(name = "TicketController",description = "排队取号接口")
public class TicketController {

    final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/insert")
    @Operation(summary = "添加排队小票")
    @SaCheckLogin
    public R insert(@Valid @RequestBody InsertTicketForm form) {
        //TODO
        if(!JSONUtil.isTypeJSONArray("1")){
            return R.error("content不是JSON数组");
        }
        return R.ok();
    }

    @PostMapping("/searchTicketById")
    @Operation(summary = "添加排队小票")
    public R searchTicketById(@Valid @RequestBody SearchTicketByIdForm form) {

        return R.ok();
    }

    @PostMapping("/searchCount")
    @Operation(summary = "添加排队小票")
    public R searchTicketCount(@Valid @RequestBody SearchTicketCountForm form) {
        Integer siteId = form.getId();
        Integer count = ticketService.getCount(siteId);
        return R.ok().put("count",count);
    }

}
