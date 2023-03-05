package com.example.sedemo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
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
@TableName("images")
public class Images implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "iid", type = IdType.AUTO)
    private Long iid;

    @TableField("cid")
    private Long cid;

    @TableField("name")
    private String name;

    @NotBlank(message = "图片路径不可为空")
    @TableField("path")
    private String path;

    @TableField("create_time")
    private LocalDateTime createTime;

}
