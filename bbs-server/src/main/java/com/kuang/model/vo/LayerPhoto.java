package com.kuang.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LayerPhoto {

    @Schema(title = "相册标题")
    private String title;

    @Schema(title = "相册id")
    private int id;

    @Schema(title = "初始显示的图片序号，默认0")
    private int start;

    @Schema(title = "相册包含的图片，数组格式")
    private List<LayerPhotoData> data;

}
