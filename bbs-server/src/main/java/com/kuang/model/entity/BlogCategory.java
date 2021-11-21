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
@TableName("ks_blog_category")
@Schema(name="BlogCategory对象", description="")
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(name = "博客分类")
    private String category;


}
