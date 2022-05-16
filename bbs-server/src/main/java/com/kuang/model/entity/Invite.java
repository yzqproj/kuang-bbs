package com.kuang.model.entity;

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
@Schema(title="Invite邀请码", description="邀请码")
public class Invite implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "自增id")
    @TableId(value= "id", type = IdType.ASSIGN_ID)
    private String id;

    @Schema(title = "邀请码")
    private String code;

    @Schema(title = "用户id")
    private String uid;

    @Schema(title = "状态 0 未使用 1 使用")
    private Integer status;

    @Schema(title = "激活时间")
    private Date activeTime;

    @Schema(title = "创建时间")
    private Date gmtCreate;


}
