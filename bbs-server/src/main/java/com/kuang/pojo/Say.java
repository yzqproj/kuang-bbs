package com.kuang.pojo;

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
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ks_say")
@Schema(name="Say对象", description="")
public class Say implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(name = "唯一id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @Schema(name = "标题")
    private String title;

    @Schema(name = "内容")
    private String content;

    @Schema(name = "时间")
    private Date gmtCreate;


}
