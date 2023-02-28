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
@TableName("users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    @TableField("name")
    private String name;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("gender")
    private Boolean gender;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField("create_time")
    private LocalDateTime createTime;


}
