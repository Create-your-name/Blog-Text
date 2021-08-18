
package com.SpringBootBlog.service.impl;

import com.SpringBootBlog.dao.mapper.SysUserMapper;
import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.service.LoginService;
import com.SpringBootBlog.service.SysUserService;
import com.SpringBootBlog.vo.ErrorCode;
import com.SpringBootBlog.vo.LoginUserVo;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private LoginService loginService;

    @Override
    public UserVo findUserVoById(Long Id) {
        SysUser sysUser =sysUserMapper.selectById(Id);
        if (sysUser ==null){
            sysUser = new SysUser();
            sysUser.setId(1L);
            sysUser.setAvatar("/static/img/logo.b3a48c0,png");
            sysUser.setNickname("刘海真棒");
        }
        UserVo userVo =new UserVo();
        BeanUtils.copyProperties(sysUser,userVo);
        return userVo;
    }



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

    @Override
    public SysUser findUserByAccount(String account) {
        LambdaQueryWrapper<SysUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUser::getAccount,account);
        queryWrapper.last(("limit 1"));

        return this.sysUserMapper.selectOne(queryWrapper);
    }

    @Override
    public void sava(SysUser sysUser) {
        /**
          *     保存用户 id 自递增
         *      分布式id  雪花算法
         *
          *@Author 刘海
          *@Data 22:24 2021/8/12
          *@Param
          *@return
          */

        this.sysUserMapper.insert(sysUser);
    }
}

