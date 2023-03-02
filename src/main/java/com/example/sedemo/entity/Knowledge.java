package com.example.sedemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
    private String title;

    @TableField("text")
    private String text;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
