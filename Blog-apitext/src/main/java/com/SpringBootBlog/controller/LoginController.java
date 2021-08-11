package com.SpringBootBlog.controller;

import com.SpringBootBlog.service.LoginService;
import com.SpringBootBlog.service.SysUserService;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {
/*    @Autowired
    private SysUserService sysUserService;*/

    @Autowired
    private LoginService loginService ;  // 爆红是因为没有实现类
    @PostMapping
    public Result login(@RequestBody LoginParam loginParam){
        //  登入  验证 用户  访问用户表

        return loginService.login(loginParam);
    }
}
