package com.kuang.pojo;

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
 * @since 2020-07-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ks_download")
@Schema(name="Download对象", description="")
public class Download implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "资源名")
    private String dname;

    @Schema(name = "资源链接")
    private String ddesc;

    @Schema(name = "提取码")
    private String dcode;


}
