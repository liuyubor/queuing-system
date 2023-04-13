package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "根据站点id获取时间段表单")
public class GetTimeSlotByIdForm {

    @NotBlank(message = "站点id不能为空")
    @Schema(description = "时间段id")
    private String id;
}
