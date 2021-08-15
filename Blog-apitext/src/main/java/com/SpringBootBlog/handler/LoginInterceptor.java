package com.SpringBootBlog.handler;

import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.service.LoginService;
import com.SpringBootBlog.utils.UserThreadLocal;
import com.SpringBootBlog.vo.ErrorCode;
import com.SpringBootBlog.vo.Result;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /**
          * 在执行controller方法 （Handler）之前 进行执行
         * 需要判断  请求的接口路径 是否为 HandlerMethod (controller 方法)
         * 判断 token 是否为空   如果为空  未登入
         * 如果 token 不为空  ， 登入验证 loginService   checkToken
         * 如果 认证成功   ，放行
          *@Author 刘海
          *@Data 14:56 2021/8/14
          *@Param
          *@return
          */
            if ( !(handler instanceof HandlerMethod) ){ // handler : 代表 controller下的某个方法   这里是TestController test
                // handler   可能是  RequestResourceHandler    springboot   程序  訪問靜態 資源 默認去 classpath 下的static 目錄去查詢
                return true ;
            }
            String token = request.getHeader("Authorization");

            log.info("=================request start===========================");
            String requestURI = request.getRequestURI();
            log.info("request uri:{}",requestURI);
            log.info("request method:{}",request.getMethod());
            log.info("token:{}", token);
            log.info("=================request end===========================");


        if ( StringUtils.isBlank(token) ) {
            Result result =Result.fail(ErrorCode.NO_LOGIN.getCode(),  "未登入");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return  false ;
        }
        SysUser sysUser =loginService.checkToken(token);
        if (sysUser == null){
            Result result =Result.fail(ErrorCode.NO_LOGIN.getCode(),  "未登入");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return  false ;
        }
        //成功
        /**
          *  获取用户信息
          *@Author 刘海
          *@Data 16:53 2021/8/15
          *@Param
          *@return
          */
        UserThreadLocal.put(sysUser);
            return true;
    }

    @Override
    /**
      *  方法 在拦截器中   代表 所有方法执行完毕  做收尾工作
     *  在线程 未关闭之前  在User用户信息 没用之后 就删掉
     *
     *      重点！！！！为什么会内存泄漏      p 17
     *
      *@Author 刘海
      *@Data 17:09 2021/8/15
      *@Param
      *@return
      */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //  不删除 ThreadLocal 中用完的信息  会有 内存泄漏的风险
        UserThreadLocal.remove();
    }
}
