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
@TableName("images")
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "iid", type = IdType.AUTO)
    private Long iid;

    @TableField("cid")
    private Long cid;

    @TableField("name")
    private String name;

    @TableField("path")
    private String path;

    @TableField("create_time")
    private LocalDateTime createTime;


}
