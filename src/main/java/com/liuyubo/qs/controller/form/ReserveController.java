package com.liuyubo.qs.controller.form;

import com.liuyubo.qs.service.ReservationService;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/reserve")
@Tag(name = "ReserveController", description = "预约接口")
public class ReserveController {
    final ReservationService reserveService;

    public ReserveController(ReservationService reserveService) {
        this.reserveService = reserveService;
    }

    @GetMapping("/selectAllReserve")
    @Operation(summary = "查询所有预约")
    public R selectAllReserve(){
        ArrayList<HashMap> list = reserveService.selectAllReserve();
        return R.ok().put("list", list);
    }


    @PostMapping("/selectReserveByPage")
    @Operation(summary = "分页查询预约")
    public R selectReserveByPage(){
        return null;
    }

    @PostMapping("/selectReserveByUserId")
    @Operation(summary = "根据用户ID查询预约")
    public R selectReserveByUserId(@Valid @RequestBody selectReserveByUserIdForm form){
        HashMap map = reserveService.selectReserveByUserId(form.getUserId());

        return R.ok().put("map", map);
    }
}
