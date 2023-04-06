package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "预约表单")
public class ReserveForm {

    @NotBlank(message = "siteId不能为空")
    @Schema(description = "场地id")
    private String siteId;

    @NotBlank(message = "username不能为空")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "userId不能为空")
    @Schema(description = "用户id")
    private String userId;

    @NotBlank(message = "tel不能为空")
    @Schema(description = "电话")
    private String tel;

    @NotBlank(message = "time不能为空")
    @Schema(description = "时间段")
    private String time;
}
