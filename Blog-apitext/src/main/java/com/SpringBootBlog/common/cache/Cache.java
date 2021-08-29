package com.SpringBootBlog.common.cache;


import java.lang.annotation.*;


//使注解生效 需要 在所对应的 Annotation类型上加上  注解所对应的  注解

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {

    long expire() default 1 * 60 * 1000;
    //缓存标识 key
    String name() default "";

}
