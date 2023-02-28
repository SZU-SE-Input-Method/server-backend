package com.example.sedemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;

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
@TableName("phrases")
public class Phrase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "pid", type = IdType.AUTO)
    private Long pid;

    @TableField("content")
    private String content;

    @TableField("uid")
    private Long uid;

    @TableField("create_time")
    private LocalDate createTime;


}
