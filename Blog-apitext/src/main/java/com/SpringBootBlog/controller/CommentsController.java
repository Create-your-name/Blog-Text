package com.SpringBootBlog.controller;

import com.SpringBootBlog.service.CommentsService;
import com.SpringBootBlog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @GetMapping("article/{id}")
    public Result comments (@PathVariable("id") Long id){
        return  commentsService.commentsByArticleId(id);
    }
}
