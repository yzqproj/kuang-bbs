package com.kuang.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LayerPhotoData {

    @Schema(title = "图片名")
    private String alt;

    @Schema(title = "图片id")
    private int pid;

    @Schema(title = "原图地址")
    private String src;

    @Schema(title = "缩略图地址")
    private String thumb;

}
