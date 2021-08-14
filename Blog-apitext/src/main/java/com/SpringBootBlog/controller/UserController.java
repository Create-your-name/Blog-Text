package com.SpringBootBlog.controller;

import com.SpringBootBlog.service.SysUserService;
import com.SpringBootBlog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token) {

        return sysUserService.findUserByToken(token);
    }
}
