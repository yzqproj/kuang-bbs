package com.kuang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.io.Serial;
import java.util.Date;
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
@TableName("ks_blog")
@Schema(name="Blog对象", description="")
public class Blog implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(name = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(name = "博客id")
    private String bid;

    @Schema(name = "博客标题")
    private String title;

    @Schema(name = "博客内容")
    private String content;

    @Schema(name = "排序 0 普通  1 置顶")
    private Integer sort;

    @Schema(name = "浏览量")
    private Integer views;

    @Schema(name = "作者id")
    private String authorId;

    @Schema(name = "作者名")
    private String authorName;

    @Schema(name = "作者头像")
    private String authorAvatar;

    @Schema(name = "问题分类id")
    private Integer categoryId;

    @Schema(name = "问题分类名称")
    private String categoryName;

    @Schema(name = "创建时间")
    private Date gmtCreate;

    @Schema(name = "修改时间")
    private Date gmtUpdate;


}
