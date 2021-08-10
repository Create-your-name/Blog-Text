package com.SpringBootBlog.service;

import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.PageParams;


public interface ArticleService {

    /*
    * 分页查询 文章列表
    * */
    Result listAreticle (PageParams pageParams);

    /**
      * 最热文章
      *@Author 刘海
      *@Data 11:04 2021/8/10
      *@Param
      *@return
      */
    Result hotArtice(int limit);
}
