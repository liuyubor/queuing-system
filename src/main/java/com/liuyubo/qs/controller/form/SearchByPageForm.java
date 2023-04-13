package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
@Schema(description = "查询分页记录表单")
public class SearchByPageForm {
    @NotNull(message = "page不能为空")
    @Min(value = 1, message = "page不能小于1")
    @Schema(description = "页数")
    private Integer currentPage;

    @NotNull(message = "size不能为空")
    @Range(min = 10, max = 50, message = "size必须在10~50之间")
    @Schema(description = "每页记录数")
    private Integer size;
}
