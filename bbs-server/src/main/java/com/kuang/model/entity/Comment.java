package com.kuang.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.io.Serial;
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
 * @since 2020-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ks_comment")
@Schema(title="Comment对象", description="")
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(title = "自增id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(title = "评论唯一id")
    private String commentId;

    @Schema(title = "1博客 2问答")
    private Integer topicCategory;

    @Schema(title = "评论主题id")
    private String topicId;

    @Schema(title = "评论者id")
    private String userId;

    @Schema(title = "评论者昵称")
    private String userName;

    @Schema(title = "评论者头像")
    private String userAvatar;

    @Schema(title = "评论内容")
    private String content;

    @Schema(title = "评论创建时间")
    private Date gmtCreate;


}
