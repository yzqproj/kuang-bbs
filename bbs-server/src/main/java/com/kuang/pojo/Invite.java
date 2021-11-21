package com.kuang.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author 遇见狂神说
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ks_invite")
@Schema(name="Invite邀请码", description="邀请码")
public class Invite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "自增id")
    @TableId(value= "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "邀请码")
    private String code;

    @Schema(name = "用户id")
    private String uid;

    @Schema(name = "状态 0 未使用 1 使用")
    private Integer status;

    @Schema(name = "激活时间")
    private Date activeTime;

    @Schema(name = "创建时间")
    private Date gmtCreate;


}
