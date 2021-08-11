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

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
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
        password = DigestUtils.md5Hex(password + slat);
        SysUser sysUser=sysUserService.findUser(account,password);
        //
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String  token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("Token"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);

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
        String userJson = redisTemplate.opsForValue().get("TOKEN" +token);
        if(StringUtils.isBlank(userJson)){
            return  null;
        }
        SysUser sysUser = JSON.parseObject(userJson ,SysUser.class);
        return sysUser;
    }
}
