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
 * <p>
 * 
 * </p>
 *
 * @author 遇见狂神说
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ks_question")
@Schema(title="Question对象", description="")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(title = "问题id")
    private String qid;

    @Schema(title = "问题标题")
    private String title;

    @Schema(title = "问题内容")
    private String content;

    @Schema(title = "状态 0 未解决 1 已解决")
    private Integer status;

    @Schema(title = "排序 0 普通  1 置顶")
    private Integer sort;

    @Schema(title = "浏览量")
    private Integer views;

    @Schema(title = "作者id")
    private String authorId;

    @Schema(title = "作者名")
    private String authorName;

    @Schema(title = "作者头像")
    private String authorAvatar;

    @Schema(title = "问题分类id")
    private Integer categoryId;

    @Schema(title = "问题分类名称")
    private String categoryName;

    @Schema(title = "创建时间")
    private Date gmtCreate;

    @Schema(title = "修改时间")
    private Date gmtUpdate;


}
