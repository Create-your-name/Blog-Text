package com.SpringBootBlog.handler;

import com.SpringBootBlog.vo.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//对 加了 @Controller注解的方法 进行拦截处理 AOP的 实现
@ControllerAdvice
public class ALLExceptionHandler {
    // 进行异常处理  ， 处理Exception.class 的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回jason 数据
    public Result doException (Exception ex){
        ex.printStackTrace();
        return  Result.fail(-999,"系统异常");
    }
}
