package com.SpringBootBlog.controller;

import com.SpringBootBlog.service.LoginService;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginService loginService;
    @PostMapping
    public Result register (@RequestBody LoginParam loginParam){
        /**
          *  sso 单点登入  后期把 登入注册功能提出去  可以独立提供 接口服务
          *@Author 刘海
          *@Data 22:05 2021/8/12
          *@Param
          *@return
          */
        return loginService.register(loginParam);
    }
}


