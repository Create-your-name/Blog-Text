package com.SpringBootBlog.service;

import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.vo.Result;

public interface SysUserService {
    SysUser findUserById (Long id);

    SysUser findUser(String account, String password);
    /**
      *  根据token 来查询用户信息
      *@Author 刘海
      *@Data 22:07 2021/8/11
      *@Param
      *@return
      */
    Result findUserByToken(String token);
}
