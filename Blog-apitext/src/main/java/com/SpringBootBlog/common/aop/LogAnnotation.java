package com.SpringBootBlog.common.aop;

import java.lang.annotation.*;

//使注解生效 需要 在所对应的 Annotation类型上加上  注解所对应的  注解

// Type  代表 可以放在类上面   Method 代表可以放在方法上
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {

    String module() default  "";

    String operator() default  "" ;
}
