package com.example.sedemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("knowledge")
public class Knowledge implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "kid", type = IdType.AUTO)
    private Long kid;

    @TableField("title")
    private String title;

    @TableField("text")
    private String text;

    @TableField("create_time")
    private LocalDateTime createTime;


}
