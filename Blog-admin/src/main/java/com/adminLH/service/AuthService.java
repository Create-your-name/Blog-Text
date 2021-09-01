package com.adminLH.service;

import com.adminLH.pojo.Admin;
import com.adminLH.pojo.Permission;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AuthService {

    @Autowired
    AdminService adminService;

    public  boolean auth(HttpServletRequest request , Authentication authentication){
        // 权限认证

        String requestURI = request.getRequestURI();
        //请求路径

        Object principal = authentication.getPrincipal();
        if (principal == null || "anonymousUser".equals(principal)){
            //未登录
            return false;
        }
        UserDetails userDetails = (UserDetails) principal;
        String username = userDetails.getUsername();
        Admin admin = adminService.findAdminByUserName(username);
        if (admin == null){
            return false;
        }

        if (admin.getId() == 1){
            //认为是超级管理员
            return true;
        }
        Long id =admin.getId();
        List<Permission> permissions = adminService.findPermissionsByAdminId(id);
        requestURI = StringUtils.split(requestURI,'?')[0];
        for (Permission permission : permissions) {
            if (requestURI.equals(permission.getPath())){
                return true;
            }
        }
        return false;

    }
}
