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

    /**
      *最新文章
      *@Author 刘海
      *@Data 14:55 2021/8/10
      *@Param
      *@return
      */
    Result newArticles(int limit);

    /**
      *   文章归档
      *@Author 刘海
      *@Data 15:17 2021/8/10
      *@Param
      *@return
      */
    Result listArchives();
    /**
      * 查看文章 详情
      *@Author 刘海
      *@Data 16:39 2021/8/16
      *@Param
      *@return
      */
    Result findArticleById(Long articleId);
}
