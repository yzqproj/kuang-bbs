package com.kuang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
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
 * @since 2020-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ks_user_info")
@Schema(title="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "用户id")
    @TableId(value = "uid", type = IdType.INPUT)
    private String uid;

    @Schema(title = "用户昵称")
    private String nickname;

    @Schema(title = "真实姓名")
    private String realname;

    @Schema(title = "QQ")
    private String qq;

    @Schema(title = "WeChat")
    private String wechat;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "手机")
    private String phone;

    @Schema(title = "工作")
    private String work;

    @Schema(title = "地址")
    private String address;

    @Schema(title = "爱好")
    private String hobby;

    @Schema(title = "自我介绍")
    private String intro;

}
