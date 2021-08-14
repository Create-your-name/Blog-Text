package com.SpringBootBlog.service.impl;

import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.service.LoginService;
import com.SpringBootBlog.service.SysUserService;
import com.SpringBootBlog.utils.JWTUtils;
import com.SpringBootBlog.vo.ErrorCode;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.LoginParam;
import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
//事务注释
@Transactional
public class LoginServiceImpl implements LoginService {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

   /* private static final String slat = "liuhai!@#";*/
   private static final String slat = "mszlu!@#";
    @Override
    public Result login(LoginParam loginParam) {
        /**
          *检查参数是否合法
         * 根据用户名和密码 去 user表中查询 是否存在
         * 不存在 登入失败    存在 使用 jwt的 生成token  返回给前端
         * token  放入 redis 当中  redis   存储  token ：user 的 对应关系   设置 过期时间 （登入认证的时候  先认证 token 字符串是否合法  去redis 认证 是否存在）
          *@Author 刘海
          *@Data 15:14 2021/8/11
          *@Param
          *@return
          */
        String account =loginParam. getAccount();
        String password = loginParam.getPassword();
        if(StringUtils.isBlank(account) || org.apache.commons.lang3.StringUtils.isBlank(password)){
                return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        // 数据库中 加密的 密码 处理过程
        String pwd = DigestUtils.md5Hex(password + slat);
        SysUser sysUser=sysUserService.findUser(account,pwd);
        //
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String  token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("Token"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        System.out.println("=========================================================Token"+token);
        return Result.success(token);
    }

    @Override
    public SysUser checkToken(String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String,Object> stringObjectMap = JWTUtils.checkToken(token);
        if (stringObjectMap == null ){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("Token" +token);

        if(StringUtils.isBlank(userJson)){
            return  null;
        }
        SysUser sysUser = JSON.parseObject(userJson ,SysUser.class);
        return sysUser;
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("Token"+token);
        return Result.success(null);
    }


    /**
      * 注册功能
      *@Author 刘海
      *@Data 22:07 2021/8/12
      *@Param
      *@return
      */
    @Override
    public Result register(LoginParam loginParam) {
        /**
          * 参数是否合法
         *  判断账户是否 存在
         *  存在 返回被注册
         *  如果账户不存在  注册用户
         *  生成token 存入 redis 返回
         *  注意加上事务 ， 一旦 中间 过程出现问题 那么 就回滚
          *@Author 刘海
          *@Data 22:07 2021/8/12
          *@Param
          *@return
          */
        String account = loginParam.getAccount();
        String password =loginParam.getPassword();
        String nickname = loginParam.getNickname();
        if( StringUtils.isBlank(account)
                || StringUtils.isBlank(password)
                || StringUtils.isBlank(nickname)){
            return  Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser = sysUserService.findUserByAccount(account);
        if(sysUser != null){
            return  Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), "账户已经被注册");
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5Hex(password+slat));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1);
        sysUser.setDeleted(0);
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        this.sysUserService.sava(sysUser);
        String  token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("Token"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);

        return Result.success(token);
    }
}
