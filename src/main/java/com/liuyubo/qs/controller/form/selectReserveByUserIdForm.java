package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "根据用户id查询预约表单")
public class SelectReserveByUserIdForm {

        @NotBlank(message = "用户id不能为空")
        @Schema(description = "用户id")
        private String userId;
}
