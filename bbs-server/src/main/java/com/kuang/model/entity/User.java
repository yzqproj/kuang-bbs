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
@Schema(name="User对象", description="")
@TableName("ks_user")
public class User extends  Model<User>  implements Serializable {


    @Serial
    private static final long serialVersionUID = 2603954883132545471L;
    @Schema(name = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "用户编号")
    private String userId;

    @Schema(name = "角色编号")
    private Integer roleId;

    @Schema(name = "用户名")
    private String username;

    @Schema(name = "密码")
    private String password;

    @Schema(name = "头像")
    private String avatar;

    @Schema(name = "登录时间")
    private Timestamp loginDate;

    @Schema(name = "创建时间")
    private Timestamp gmtCreate;


}
