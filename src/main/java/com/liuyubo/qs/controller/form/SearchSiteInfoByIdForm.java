package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(name = "searchSiteInfoByIdForm", description = "根据ID查询核酸站点信息")
public class SearchSiteInfoByIdForm {

    @NotNull(message = "id不能为空")
    @Schema(description = "核酸站点id")
    private Integer id;
}
