package com.SpringBootBlog.service;

import com.SpringBootBlog.dao.pojo.SysUser;

public interface SysUserService {
    SysUser findUserById (Long id);
}
