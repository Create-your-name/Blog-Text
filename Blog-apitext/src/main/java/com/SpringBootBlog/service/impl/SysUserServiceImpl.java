
package com.SpringBootBlog.service.impl;

import com.SpringBootBlog.dao.mapper.SysUserMapper;
import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser =sysUserMapper.selectById(id);
        if (sysUser ==null){
            sysUser = new SysUser();
            sysUser.setNickname("刘海真棒");
        }
        return sysUser;
    }
}

