package com.liuyubo.qs.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Schema(description = "修改用户信息表单")
public class UpdateUserAForm {

    @NotNull(message = "id不能为空")
    @Schema(description = "userId")
    private Integer id;

    @Schema(description = "姓名")
    private String username;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "电话")
    private String tel;

    @Schema(description = "角色")
    private String role;

}
