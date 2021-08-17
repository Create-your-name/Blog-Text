package com.SpringBootBlog.service;

import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.UserVo;

public interface SysUserService {

    UserVo findUserVoById(Long Id);
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
    /**
      *     注册功能  查找用户
      *@Author 刘海
      *@Data 22:20 2021/8/12
      *@Param
      *@return
      */
    SysUser findUserByAccount(String account);

    /**
      * 保存用户
      *@Author 刘海
      *@Data 22:21 2021/8/12
      *@Param
      *@return
      */
    void sava(SysUser sysUser);
}
