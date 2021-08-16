package com.SpringBootBlog.controller;

import com.SpringBootBlog.service.ArticleService;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//json数据交互
@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    /*
    * 首页文章列表
    * */
    @PostMapping
        public Result listArticle(@RequestBody PageParams pageParams){
        return articleService.listAreticle(pageParams);
    }
    /**
      *首页 最热文章
      *@Author 刘海
      *@Data 14:53 2021/8/10
      *@Param
      *@return
      */
    @PostMapping("hot")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotArtice(limit);
    }
    /**
      * 最新文章
      *@Author 刘海
      *@Data 14:54 2021/8/10
      *@Param
      *@return
      */
    @PostMapping("new")
    public Result newArticles(){
        int limit = 5;
        return articleService.newArticles(limit);
    }

    /**
     * 最新文章
     *@Author 刘海
     *@Data 14:54 2021/8/10
     *@Param
     *@return
     */
    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return articleService.findArticleById(articleId);
    }
}
