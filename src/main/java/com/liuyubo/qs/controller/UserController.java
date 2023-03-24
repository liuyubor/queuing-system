package com.liuyubo.qs.controller;

import cn.hutool.json.JSONUtil;
import com.liuyubo.qs.controller.form.RegisterForm;
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

    @GetMapping("/login")
    @Operation(summary = "登录")
    public R login(@RequestParam("openId") String openId) {
        Integer userId = userService.searchIdByOpenId(openId);
        if (userId == null) {
            return R.error("用户不存在");
        }
        /**
         * userSummary
         * {
         *    "id": 1,
         *    "username": "liuyubo",
         *    "nickname": "liuyubo",
         *    "photo": "https://liuyubo-1258579779.cos.ap-shanghai.myqcloud.com/2021/07/31/1627700000.png",
         *    "tel": "12345678901",
         *    "roles": [2,3]
         *    "openId": "oQKgO5QZ0Z0Z0Z0Z0Z0Z0Z0Z0Z0Z0"
         *    }
         */
        HashMap userSummary = userService.searchUserSummary(userId);
        return R.ok(userSummary);

    }

    @GetMapping("/loadUserInfo")
    @Operation(summary = "加载用户信息")
    public R loadUserInfo(@RequestParam("openId") String openId) {
        HashMap userinfo = userService.loadUserInfo(openId);
        return R.ok(userinfo);
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

    @PostMapping("/update")
    @Operation(summary = "修改用户")
    public R update(@Valid @RequestBody UpdateUserForm form) {
        HashMap param = JSONUtil.parse(form).toBean(HashMap.class);
        int rows = userService.update(param);
        return R.ok().put("rows", rows);
    }


}
