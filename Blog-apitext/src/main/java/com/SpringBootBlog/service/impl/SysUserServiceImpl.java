
package com.SpringBootBlog.service.impl;

import com.SpringBootBlog.dao.mapper.SysUserMapper;
import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.service.LoginService;
import com.SpringBootBlog.service.SysUserService;
import com.SpringBootBlog.vo.ErrorCode;
import com.SpringBootBlog.vo.LoginUserVo;
import com.SpringBootBlog.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private LoginService loginService;
    @Override
    public SysUser findUserById(Long id) {
        SysUser sysUser =sysUserMapper.selectById(id);
        if (sysUser ==null){
            sysUser = new SysUser();
            sysUser.setNickname("刘海真棒");
        }
        return sysUser;
    }

    @Override                               // 密码 理论上是加密的
    public SysUser findUser(String account, String password) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.eq(SysUser::getPassword,password);
        queryWrapper.select(SysUser::getAccount,SysUser::getId,SysUser::getAvatar,SysUser::getNickname);
        queryWrapper.last("limit 1");

        return sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public Result findUserByToken(String token) {
        /**
          * 1.token  合法性校验
         * 是否为空 解析是否成功  redis 是否存在
         *  2.如果 校验失败  返回 错误
         *  3. 如果成功  返回对应结果     LoginUserVo
          *@Author 刘海
          *@Data 22:08 2021/8/11
          *@Param
          *@return
          */

        SysUser sysUser = loginService.checkToken(token);
        if(StringUtils.isBlank(token)){
            Result.fail(ErrorCode.TOKEN_ERROR.getCode(),ErrorCode.TOKEN_ERROR.getMsg());
        }
        LoginUserVo loginUserVo = new LoginUserVo() ;
        loginUserVo.setId(sysUser.getId());
        loginUserVo.setNickname(sysUser.getNickname());
        loginUserVo.setAvatar(sysUser.getAvatar());
        loginUserVo.setAccount(sysUser.getAccount());
        return Result.success(loginUserVo);
    }
}

