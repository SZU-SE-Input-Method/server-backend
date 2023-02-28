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
 * @author author
 * @since 2023-02-27
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

    @TableField("create_time")
    private LocalDateTime createTime;


}
