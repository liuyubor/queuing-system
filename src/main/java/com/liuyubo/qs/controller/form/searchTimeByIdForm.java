package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "核酸站点ID")
public class SearchTimeByIdForm {

    @NotNull(message = "ids不能为空")
    @Schema(description = "核酸站点ID")
    private Integer id;
}
