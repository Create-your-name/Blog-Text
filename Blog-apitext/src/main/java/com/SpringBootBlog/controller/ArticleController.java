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
    @PostMapping("hot")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotArtice(limit);
    }
}
