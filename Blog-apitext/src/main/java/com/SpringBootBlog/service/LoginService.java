package com.SpringBootBlog.service;

import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.LoginParam;
import org.springframework.transaction.annotation.Transactional;

@Transactional
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
    /**
      *     退出登入功能
      *@Author 刘海
      *@Data 15:28 2021/8/12
      *@Param
      *@return
      */
    Result logout(String token);
    /**
      * 注册功能
      *@Author 刘海
      *@Data 22:06 2021/8/12
      *@Param
      *@return
      */
    Result register(LoginParam loginParam);
}
