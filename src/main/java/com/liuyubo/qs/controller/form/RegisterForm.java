package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(description = "注册表单")
public class RegisterForm {

    @NotBlank(message = "photo不能为空")
    @Schema(description = "用户头像")
    private String photo;

    @NotBlank(message = "openid不能为空")
    @Schema(description = "openId")
    private String openId;

    @NotBlank(message = "nickname不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,32}$", message = "nickname内容不正确")
    @Schema(description = "用户名")
    private String nickname;

    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,32}$", message = "password内容不正确")
    @Schema(description = "密码")
    private String password;

    @NotBlank(message = "name不能为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]{2,10}$", message = "username内容不正确")
    @Schema(description = "姓名")
    private String username;

    @NotBlank(message = "tel不能为空")
    @Pattern(regexp = "^1\\d{10}$", message = "tel内容不正确")
    @Schema(description = "电话")
    private String tel;
}
