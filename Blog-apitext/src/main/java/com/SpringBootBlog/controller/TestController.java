package com.SpringBootBlog.controller;

import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.utils.UserThreadLocal;
import com.SpringBootBlog.vo.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @RequestMapping
    public Result test(){
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return  Result.success(null);
    }
}
