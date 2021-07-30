package com.SpringBootBlog.service.impl;

import com.SpringBootBlog.dao.mapper.ArticleMapper;
import com.SpringBootBlog.dao.pojo.Article;
import com.SpringBootBlog.service.ArticleService;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.PageParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Result listAreticle(PageParams pageParams) {
         /*
         *  1. 分页查询 article 数据库表
         * */
        Page<Article> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryChainWrapper <Article> queryChainWrapper =new LambdaQueryWrapper<>()
        articleMapper.selectPage()
    }
}
