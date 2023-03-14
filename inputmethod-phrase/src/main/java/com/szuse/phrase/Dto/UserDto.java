package com.szuse.phrase.Dto;

import com.szuse.phrase.entity.User;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author LiuYe
 * @version 1.0
 * @date 1/3/2023 下午8:52
 */
@Data
public class UserDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long uid;

    @NotBlank(message = "用户名不可为空")
    private String name;
    @NotBlank(message = "用户账号不可为空")
    private String username;
    @NotBlank(message = "密码不可为空")
    private String password;

    private String gender;

    private String phone;

    @Email(message = "邮箱格式不正确")
    private String email;

    private LocalDateTime createTime;

    public static UserDto userToDTO(User user) {
        UserDto userDTO = new UserDto();
        BeanUtils.copyProperties(user, userDTO, "gender");
        if(user.getGender() != null) {
            userDTO.setGender(user.getGender() ? "男" : "女");
        }
        return userDTO;
    }
}
