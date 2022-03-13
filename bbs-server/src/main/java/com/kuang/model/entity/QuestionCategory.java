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
 * @since 2020-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ks_question_category")
@Schema(title="QuestionCategory对象", description="")
public class QuestionCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(title = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(title = "问题分类")
    private String category;


}
