package com.liuyubo.qs.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.InsertUserForm;
import com.liuyubo.qs.controller.form.UpdatePasswordForm;
import com.liuyubo.qs.controller.form.UpdateUserForm;
import com.liuyubo.qs.controller.form.WechatLoginForm;
import com.liuyubo.qs.db.POJO.User;
import com.liuyubo.qs.service.impl.UserServiceImpl;
import com.liuyubo.qs.utils.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Tag(name = "UserController", description = "用户Web接口")
public class UserController {

    @Value("${wx.appid}")
    private String appid;

    @Value("${wx.secret}")
    private String secret;

    @Value("${wx.grant_type}")
    private String grant_type;

    @Value("${wx.login_url}")
    private String login_url;

    final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/code2uuid")
    @Operation(summary = "转换登录code")
    public R code2uuid(@Valid @RequestBody WechatLoginForm form) {
        String url = login_url + "?js_code="+form.getCode()+
                "&appid="+appid+"&secret="+secret+"&grant_type="+grant_type;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).get().build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            // 获取结果
            String result = response.body().string();
            HashMap map = JSONUtil.parse(result).toBean(HashMap.class);
            System.out.println(map);
            return R.ok(map);
        } catch (SocketTimeoutException e) {
            return R.error("签名验证请求超时异常");
        } catch (Exception e) {
            return R.error("签名验证失败出现异常");
        }
    }

    @PostMapping("/wechatLogin")
    @Operation(summary = "微信小程序登陆")
    public R wechatLogin(@Valid @RequestBody WechatLoginForm form) {
        HashMap map = userService.wechatLogin(form.getCode());
        boolean result = (boolean) map.get("result");
        if (result) {
            int userId = (int) map.get("userId");
            StpUtil.login(userId);
            Set<String> permissions = userService.searchUserPermissions(userId);
            map.remove("userId");
            map.put("permissions", permissions);
            String token=StpUtil.getTokenValue();
            map.put("token",token);
        }
        return R.ok(map);
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
//    @SaCheckPermission(value = {"ROOT", "USER:INSERT"}, mode = SaMode.OR)
    @Operation(summary = "注册系统")
    public R register(@Valid @RequestBody InsertUserForm form){
        User user = JSONUtil.parse(form).toBean(User.class);
//        user.setRole(JSONUtil.parseArray(form.getRole()).toString());
        user.setRole("用户");
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
