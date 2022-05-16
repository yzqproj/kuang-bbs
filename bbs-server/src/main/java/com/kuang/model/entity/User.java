package com.kuang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.io.Serial;
import java.sql.Timestamp;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 遇见狂神说
 * @since 2020-06-28
 */
@Data
@Schema(title="User对象", description="")
@TableName("ks_user")
public class User extends  Model<User>  implements Serializable {


    @Serial
    private static final long serialVersionUID = 2603954883132545471L;
    @Schema(title = "自增id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(title = "用户编号")
    private String userId;

    @Schema(title = "角色编号")
    private Integer roleId;

    @Schema(title = "用户名")
    private String username;

    @Schema(title = "密码")
    private String password;

    @Schema(title = "头像")
    private String avatar;

    @Schema(title = "登录时间")
    private Timestamp loginDate;

    @Schema(title = "创建时间")
    private Timestamp gmtCreate;


}
