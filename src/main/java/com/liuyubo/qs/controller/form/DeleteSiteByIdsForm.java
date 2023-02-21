package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(description = "删除核酸站点表单")
public class DeleteSiteByIdsForm {
    @NotEmpty(message = "ids不能为空")
    @Schema(description = "角色ID")
    private Integer[] ids;
}
