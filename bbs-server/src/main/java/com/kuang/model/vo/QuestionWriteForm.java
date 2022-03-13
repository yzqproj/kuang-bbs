package com.kuang.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuestionWriteForm {

    @Schema(title = "问题标题")
    private String title;
    @Schema(title = "问题内容")
    private String content;

    @Schema(title = "问题分类id")
    private Integer categoryId;

    @Schema(title = "作者id")
    private String authorId;
    @Schema(title = "作者名称")
    private String authorName;
    @Schema(title = "作者头像")
    private String authorAvatar;

}
