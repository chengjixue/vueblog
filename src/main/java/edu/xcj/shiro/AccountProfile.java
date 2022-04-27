package edu.xcj.shiro;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/4/27 21:43
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;

    private String username;

    private String avatar;

    private String email;

    private LocalDateTime created;

}
