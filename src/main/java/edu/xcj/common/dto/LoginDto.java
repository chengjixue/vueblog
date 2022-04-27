package edu.xcj.common.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/4/27 22:37
 */
@Data
public class LoginDto implements Serializable {
    @NotEmpty(message = "用户名不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;


}
