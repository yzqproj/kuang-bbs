package com.kuang.pojo;

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
@Schema(name="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "用户id")
    @TableId(value = "uid", type = IdType.INPUT)
    private String uid;

    @Schema(name = "用户昵称")
    private String nickname;

    @Schema(name = "真实姓名")
    private String realname;

    @Schema(name = "QQ")
    private String qq;

    @Schema(name = "WeChat")
    private String wechat;

    @Schema(name = "邮箱")
    private String email;

    @Schema(name = "手机")
    private String phone;

    @Schema(name = "工作")
    private String work;

    @Schema(name = "地址")
    private String address;

    @Schema(name = "爱好")
    private String hobby;

    @Schema(name = "自我介绍")
    private String intro;

}
