package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "修改核算站点信息表单")
public class UpdateSiteForm {

    /*
                    id: this.data.siteId,
                name: this.data.name,
                description: this.data.desp,
                capacity: this.data.capacity,
                status: this.data.stats,
                timeSlots: this.data.result
     */
    @NotNull(message = "核酸站点ID不能为空")
    @Schema(description = "核酸站点ID")
    private Integer id;

    private String name;
    private String description;
    private Integer capacity;
    private Integer status;
    private String timeSlots;
}
