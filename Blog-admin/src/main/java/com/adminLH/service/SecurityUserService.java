package com.adminLH.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.adminLH.mapper.AdminMapper;
import com.adminLH.pojo.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class SecurityUserService implements UserDetailsService {
    @Autowired
    private AdminService adminService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
/*        log.info("username:{}",username);*/
        //当用户登录的时候，springSecurity 就会将请求 转发到此
        //根据用户名 查找用户，不存在 抛出异常，存在 将用户名，密码，授权列表 组装成springSecurity的User对象 并返回
        Admin adminUser = adminService.findAdminByUserName(username);
        if (adminUser == null){
            //登入失败
            throw new UsernameNotFoundException("用户名不存在");
        }
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        // 框架的 认证
        UserDetails userDetails = new User(username,adminUser.getPassword(),new ArrayList<>());
        //剩下的认证 就由框架帮我们完成
        return userDetails;
    }
}