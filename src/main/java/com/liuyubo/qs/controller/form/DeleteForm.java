package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "删除表单")
public class DeleteForm {

    @NotBlank(message = "id不能为空")
    @Schema(description = "要删除的id")
    private String id;
}
