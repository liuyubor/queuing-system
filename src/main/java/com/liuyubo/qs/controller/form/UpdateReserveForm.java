package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "更新预约表单")
public class UpdateReserveForm {

    @NotBlank(message = "id不能为空")
    @Schema(description = "预约id")
    private String id;

    @Schema(description = "预约站点id")
    private String siteId;

    @Schema(description = "预约时间段id")
    private String timeId;

    @Schema(description = "预约状态")
    private String status;

}
