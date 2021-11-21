package com.kuang.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LayerPhoto {

    @Schema(name = "相册标题")
    private String title;

    @Schema(name = "相册id")
    private int id;

    @Schema(name = "初始显示的图片序号，默认0")
    private int start;

    @Schema(name = "相册包含的图片，数组格式")
    private List<LayerPhotoData> data;

}
