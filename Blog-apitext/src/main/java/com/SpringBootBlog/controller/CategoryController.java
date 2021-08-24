package com.SpringBootBlog.controller;

import com.SpringBootBlog.service.CategoryService;
import com.SpringBootBlog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("categorys")    //前端路径
public class CategoryController{

    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public Result categories (){
        return  categoryService.findAll();
    }
}
