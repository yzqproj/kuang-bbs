package com.kuang.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class QuestionWriteForm {

    @Schema(name = "问题标题")
    private String title;
    @Schema(name = "问题内容")
    private String content;

    @Schema(name = "问题分类id")
    private Integer categoryId;

    @Schema(name = "作者id")
    private String authorId;
    @Schema(name = "作者名称")
    private String authorName;
    @Schema(name = "作者头像")
    private String authorAvatar;

}
