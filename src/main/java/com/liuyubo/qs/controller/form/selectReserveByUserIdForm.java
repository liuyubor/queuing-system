package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "根据用户id查询预约表单")
public class SelectReserveByUserIdForm {

        @Schema(description = "用户id")
        private String userId;
}
