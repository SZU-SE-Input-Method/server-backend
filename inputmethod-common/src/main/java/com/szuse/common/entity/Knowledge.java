package com.szuse.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 *
 * @author LiuHongLie
 * @since
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("knowledge")
public class Knowledge implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "kid", type = IdType.AUTO)
    private Long kid;

    @TableField("title")
    @NotBlank(message = "知识标题不可为空")
    private String title;

    @TableField("text")
    @NotBlank(message = "知识内容不可为空")
    private String text;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
