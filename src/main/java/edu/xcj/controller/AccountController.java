package edu.xcj.controller;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.xcj.common.dto.LoginDto;
import edu.xcj.common.lang.Result;
import edu.xcj.entity.User;
import edu.xcj.service.UserService;
import edu.xcj.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

/**
 * @author xuecj
 * @version 1.0
 * @date 2022/4/27 22:35
 */
@RestController
public class AccountController {
    @Autowired
    UserService userService;
    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
//      断言处理
        Assert.notNull(user, "用户不存在");
        if (user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("密码错误");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return Result.succ(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }


    @RequiresAuthentication
    @GetMapping("logout")
    public Result logout(){
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }



}
