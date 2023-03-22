package com.liuyubo.qs.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.RegisterForm;
import com.liuyubo.qs.controller.form.UpdatePasswordForm;
import com.liuyubo.qs.controller.form.UpdateUserForm;
import com.liuyubo.qs.controller.form.WechatLoginForm;
import com.liuyubo.qs.db.POJO.User;
import com.liuyubo.qs.service.impl.UserServiceImpl;
import com.liuyubo.qs.utils.R;
import com.tencent.cloud.CosStsClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

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

    @Value("${tencent.cloud.secretId}")
    private String secretId;

    @Value("${tencent.cloud.secretKey}")
    private String secretKey;

    @Value("${tencent.cloud.bucket}")
    private String bucket;

    @Value("${tencent.cloud.region}")
    private String region;

    final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/code2uuid")
    @Operation(summary = "转换登录code")
    public R code2uuid(@Valid @RequestBody WechatLoginForm form) {
        String url = login_url + "?js_code=" + form.getCode() +
                "&appid=" + appid + "&secret=" + secret + "&grant_type=" + grant_type;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).get().build();

        try (Response response = okHttpClient.newCall(request).execute()) {
            // 获取结果
            String result = response.body().string();
            HashMap map = JSONUtil.parse(result).toBean(HashMap.class);
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
        int id = userService.searchIdByOpenId(form.getCode());
        if (true) {
            int userId = (int) map.get("userId");
            Set<String> permissions = userService.searchUserPermissions(userId);
            map.remove("userId");
            map.put("permissions", permissions);
        }

        map.put("id",id);
        return R.ok(map);
    }

    @GetMapping("/upload")
    @Operation(summary = "上传头像")
    public R upload() {
        TreeMap<String, Object> config = new TreeMap<String, Object>();
        try {
            config.put("secretId", secretId);
            config.put("secretKey", secretKey);
            config.put("bucket", bucket);
            config.put("region", region);
            config.put("allowPrefixes", new String[]{"*"});
            String[] allowActions = new String[]{
                    // 简单上传
                    "name/cos:PutObject",
                    // 表单上传、小程序上传
                    "name/cos:PostObject",
                    // 分块上传
                    "name/cos:InitiateMultipartUpload",
                    "name/cos:ListMultipartUploads",
                    "name/cos:ListParts",
                    "name/cos:UploadPart",
                    "name/cos:CompleteMultipartUpload"
            };
            config.put("allowActions", allowActions);
            com.tencent.cloud.Response response = CosStsClient.getCredential(config);
            return R.ok().put("data", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.error();
    }

    @PostMapping("/register")
    @Operation(summary = "注册")
    public R register(@Valid @RequestBody RegisterForm form) {
        User user = JSONUtil.parse(form).toBean(User.class);
        user.setRole(1);
        user.setRoot(0);
        Integer rows = userService.insert(user);
        return R.ok().put("rows",rows);
    }

    @GetMapping("/loadUserInfo")
    @Operation(summary = "登陆成功后加载用户的基本信息")
    public R loadUserInfo() {
        HashMap summary = userService.searchUserSummary(userId);
        return R.ok(summary);
    }

    @PostMapping("/updatePassword")
    @Operation(summary = "修改密码")
    public R updatePassword(@Valid @RequestBody UpdatePasswordForm form) {
        HashMap param = new HashMap() {{
            put("password", form.getPassword());
        }};
        int rows = userService.updatePassword(param);
        return R.ok().put("rows", rows);
    }


    @PostMapping("/update")
    @Operation(summary = "修改用户")
    public R update(@Valid @RequestBody UpdateUserForm form) {
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        int rows = userService.update(param);
        return R.ok().put("rows", rows);
    }


}
