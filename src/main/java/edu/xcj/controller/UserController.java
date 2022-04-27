package edu.xcj.controller;


import edu.xcj.common.lang.Result;
import edu.xcj.entity.User;
import edu.xcj.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xuecj
 * @since 2022-04-27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @RequiresAuthentication
    @GetMapping("/index")
    public Result index(){
        User user = userService.getById(1);
        return Result.succ(user);
    }



}
