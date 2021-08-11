package com.SpringBootBlog.service;

import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.LoginParam;

public interface LoginService {
    /**
      * 登入功能
      *@Author 刘海
      *@Data 15:13 2021/8/11
      *@Param
      *@return
      */
    Result login(LoginParam loginParam);

    SysUser checkToken(String token);
}
