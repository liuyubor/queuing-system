package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "添加核酸站点表单")
public class AddSiteForm {

    @NotBlank(message = "name不能为空")
    @Schema(description = "核酸站点名称")
    private String name;

    @NotBlank(message = "description不能为空")
    @Schema(description = "核酸站点描述")
    private String description;

    @NotBlank(message = "capacity不能为空")
    @Schema(description = "核酸站点容量")
    private String capacity;

    @NotNull(message = "timeSlots不能为空")
    @Schema(description = "核酸站点时间段")
    private List<Integer> timeSlots;

}
