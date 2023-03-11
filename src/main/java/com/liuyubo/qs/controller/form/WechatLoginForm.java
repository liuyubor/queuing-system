package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Schema(name = "WechatLoginForm", description = "微信小程序登陆Form类")
@Data
public class WechatLoginForm {

    @Schema(description = "code")
    @NotBlank(message = "code不能为空")
    private String code;

}
