package com.kuang.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LayerPhotoData {

    @Schema(name = "图片名")
    private String alt;

    @Schema(name = "图片id")
    private int pid;

    @Schema(name = "原图地址")
    private String src;

    @Schema(name = "缩略图地址")
    private String thumb;

}
