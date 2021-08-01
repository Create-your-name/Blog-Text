package com.SpringBootBlog.service.impl;

import com.SpringBootBlog.dao.mapper.ArticleMapper;
import com.SpringBootBlog.dao.pojo.Article;
import com.SpringBootBlog.service.ArticleService;
import com.SpringBootBlog.vo.ArticleVo;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.PageParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Result listAreticle(PageParams pageParams) {
         /*
         *  1. 分页查询 article 数据库表
         * */
        Page<Article> page = new Page<>(pageParams.getPage(),pageParams.getPageSize());
        LambdaQueryWrapper <Article> queryWrapper =new LambdaQueryWrapper<>();
        //  是否置顶排序
        //queryWrapper.orderByDesc(Article::getWeight);

        //order by create_data desc
        //           orderByDesc(可以有多个函数 所以 插入)
        queryWrapper.orderByDesc(Article::getWeight,Article::getCreateDate);
        Page<Article> articlePage =articleMapper.selectPage(page,queryWrapper);
        List<Article> recodes = articlePage.getRecords();
        //不能直接返回
        List<ArticleVo> articlevOList = copyList(recodes);
        return  Result.success(articlevOList);
    }

    private List<ArticleVo> copyList(List<Article> recodes) {
    List<ArticleVo> articleVoList =new ArrayList<>();
    for (Article record : recodes){
        articleVoList.add(copy(record));
    }
        return articleVoList;
    }
    private  ArticleVo copy (Article article){
        ArticleVo articleVo =new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH：mm"));
        return articleVo;
    }
}
