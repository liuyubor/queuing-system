package com.liuyubo.qs.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.liuyubo.qs.controller.form.DeleteSiteByIdsForm;
import com.liuyubo.qs.db.POJO.Site;
import com.liuyubo.qs.service.SiteService;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
@RequestMapping("/site")
@SaCheckLogin
@Tag(name = "SiteController", description = "核酸站点接口")
public class SiteController {

    final SiteService siteService;

    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping("/searchAllSite")
    @Operation(summary = "查询所有核酸站点")
    public R searchAllSite() {
        ArrayList<HashMap> list = siteService.searchAllSite();
        return R.ok().put("list", list);
    }

    @PostMapping("insert")
    @Operation(summary = "增加核酸站点")
    //@SaCheckPermission(value = {"ROOT", "USER:UPDATE"}, mode = SaMode.OR)
    public R insert(@Valid @RequestBody Site site) {
        Integer rows = siteService.insert(site);
        return R.ok().put("rows",rows);
    }

    @PostMapping("update")
    @Operation(summary = "修改核酸站点")
    public R update(@Valid @RequestBody Site site) {
        Integer rows = siteService.update(site);
        return R.ok().put("Rows",rows);
    }

    @PostMapping("deleteByIds")
    @Operation(summary = "删除核酸站点")
    public R deleteByIds(@Valid @RequestBody DeleteSiteByIdsForm form) {
        Integer rows = siteService.deleteSiteByIds(form.getIds());
        return R.ok().put("Rows",rows);
    }
}
