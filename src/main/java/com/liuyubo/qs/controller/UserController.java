package com.liuyubo.qs.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.InsertUserForm;
import com.liuyubo.qs.controller.form.LoginForm;
import com.liuyubo.qs.controller.form.UpdatePasswordForm;
import com.liuyubo.qs.controller.form.UpdateUserForm;
import com.liuyubo.qs.db.POJO.User;
import com.liuyubo.qs.service.impl.UserServiceImpl;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "用户Web接口")
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @PostMapping("/login")
    @Operation(summary = "登录系统")
    public R login(@Valid @RequestBody LoginForm form){
        HashMap hashMap = JSONUtil.parse(form).toBean(HashMap.class);
        Integer userId = userService.login(hashMap);
        R r = R.ok();
        if (userId != null){
            StpUtil.login(userId);
            String permissions = userService.searchUserPermissions(userId);
            String token = StpUtil.getTokenValue();
            r.put("result", true).put("permission",permissions)
                    .put("token",token);
        }else{
            r.put("result", false);
        }
        return r;
    }

    @GetMapping("/logout")
    @Operation(summary = "退出系统")
    public R logout(){
        StpUtil.logout();
        return R.ok();
    }

    @PostMapping("/updatePassword")
    @SaCheckLogin
    @Operation(summary = "修改密码")
    public R updatePassword(@Valid @RequestBody UpdatePasswordForm form){
        int userId=StpUtil.getLoginIdAsInt();
        HashMap param=new HashMap(){{
            put("userId",userId);
            put("password",form.getPassword());
        }};
        int rows=userService.updatePassword(param);
        return R.ok().put("rows",rows);
    }

    @PostMapping("insert")
    @SaCheckPermission(value = {"ROOT", "USER:INSERT"}, mode = SaMode.OR)
    @Operation(summary = "注册系统")
    public R register(@Valid @RequestBody InsertUserForm form){
        User user = JSONUtil.parse(form).toBean(User.class);
        user.setRole(JSONUtil.parseArray(form.getRole()).toString());
        user.setCreateTime(new Date());
        int rows=userService.insertUser(user);
        return R.ok().put("rows",rows);
    }

    @PostMapping("/update")
    @SaCheckPermission(value = {"ROOT", "USER:UPDATE"}, mode = SaMode.OR)
    @Operation(summary = "修改用户")
    public R update(@Valid @RequestBody UpdateUserForm form){
        HashMap param=JSONUtil.parse(form).toBean(HashMap.class);
        param.replace("role",JSONUtil.parseArray(form.getRole()).toString());
        int rows=userService.update(param);
        if(rows==1){
            StpUtil.logout(form.getUserId());
        }
        return R.ok().put("rows",rows);
    }




}
