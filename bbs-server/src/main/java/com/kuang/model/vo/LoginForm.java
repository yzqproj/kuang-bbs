package com.kuang.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 注册表单
 *
 * @author yanni
 * @date 2021/11/21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LoginForm {

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "密码")
    private String password;



}
