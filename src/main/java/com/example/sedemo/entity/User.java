package com.example.sedemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.example.sedemo.Dto.UserDto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

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
@TableName("users")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    @TableField("name")
    private String name;

    @NotBlank(message = "用户账号不可为空")
    @TableField("username")
    private String username;

    @NotBlank(message = "用户密码不可为空")
    @TableField("password")
    private String password;

    @TableField("gender")
    private Boolean gender;

    @TableField("phone")
    private String phone;

    @TableField("email")
    private String email;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    public static User dtoToUser(UserDto userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user, "gender");
        if (StringUtils.hasText(userDTO.getGender())) {
            user.setGender("男".equals(userDTO.getGender()));
        }
        return user;
    }
}
