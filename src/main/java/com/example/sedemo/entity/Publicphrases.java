package com.example.sedemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author LiuYe
 * @since 2023-02-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("publicphrases")
public class Publicphrases implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ppid", type = IdType.AUTO)
    private Long ppid;

    @TableField("content")
    private String content;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;


}
