package com.SpringBootBlog.controller;

import com.SpringBootBlog.service.ArticleService;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
