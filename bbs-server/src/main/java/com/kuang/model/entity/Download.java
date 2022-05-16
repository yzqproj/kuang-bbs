package com.kuang.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
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
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ks_download")
@Schema(title = "Download对象", description = "")
public class Download implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    @Schema(title = "id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @Schema(title = "资源名")
    private String dname;

    @Schema(title = "资源链接")
    private String ddesc;

    @Schema(title = "提取码")
    private String dcode;


}
