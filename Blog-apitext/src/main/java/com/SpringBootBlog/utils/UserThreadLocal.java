package com.SpringBootBlog.utils;

import com.SpringBootBlog.dao.pojo.SysUser;

public class UserThreadLocal {

    private  UserThreadLocal(){}
    //线程变量隔离
    // 存储单独的一个线程中的 变量
    // 存储的 东西没有联系
    //  解决 线程安全问题
    private  static  final  ThreadLocal<SysUser> LOCAL =new ThreadLocal<>();

    public  static void put (SysUser sysUser){
        LOCAL.set(sysUser);
    }
    public  static  SysUser get(){
        return  LOCAL.get();
    }

    public  static  void remove(){
        LOCAL.remove();
    }
}
