package com.SpringBootBlog.service;

import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.PageParams;


public interface ArticleService {

    /*
    * 分页查询 文章列表
    * */
    Result listAreticle (PageParams pageParams);
}
