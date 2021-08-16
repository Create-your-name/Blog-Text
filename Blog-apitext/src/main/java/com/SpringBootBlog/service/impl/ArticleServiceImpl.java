package com.SpringBootBlog.service.impl;

import com.SpringBootBlog.dao.dos.Archives;
import com.SpringBootBlog.dao.mapper.ArticleBodyMapper;
import com.SpringBootBlog.dao.mapper.ArticleMapper;
import com.SpringBootBlog.dao.pojo.Article;
import com.SpringBootBlog.dao.pojo.ArticleBody;
import com.SpringBootBlog.service.*;
import com.SpringBootBlog.vo.ArticleBodyVo;
import com.SpringBootBlog.vo.ArticleVo;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.params.PageParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;
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
        List<ArticleVo> articlevOList = copyList(recodes,true,true);
        return  Result.success(articlevOList);
    }

    @Override
    public Result hotArtice(int limit) {
       /* LambdaQueryWrapper <Article> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getId,Article::getTitle);
        queryWrapper.last("limit" +limit);
        // select id ,title from article order by view_counts desc limit s
        List<Article> articles = articleMapper.selectList(queryWrapper);
        return Result.success(copyList(articles,false,false));*/

        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getViewCounts);
        queryWrapper.select(Article::getId,Article::getTitle);
        queryWrapper.last("limit "+limit);
        //select id,title from article order by view_counts desc limit 5
        List<Article> articles = articleMapper.selectList(queryWrapper);

        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result newArticles(int limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Article::getCreateDate);
        queryWrapper.select(Article::getId,Article::getTitle);
        queryWrapper.last("limit "+limit);
        //select id,title from article order by create_date desc limit 5
        List<Article> articles = articleMapper.selectList(queryWrapper);

        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result listArchives() {
        List<Archives> archivesList =articleMapper.listArchives();
        return  Result.success(archivesList);
    }


    @Autowired
    private ThreadService threadService;
    @Override
    public Result findArticleById(Long articleId) {
        /**
          *      根据id 查询 文章信息
         *       根据bodyId  和 categoryid  去做关联查询
         *
          *@Author 刘海
          *@Data 16:40 2021/8/16
          *@Param
          *@return
          */
        Article article =this.articleMapper.selectById(articleId);

        ArticleVo articleVo= copy(article,true,true,true,true);

        /**
          *     查看玩文章了 新增阅读数
         *      查看完 文章后  应该返回数据  这时候做了一个更新操作  更新是 加写锁  阻塞 读操作 性能低
         *      更新 增加了接口实现的耗时   如果 更新出问题 不能影响  查看文章的 操作
          *@Author 刘海
          *@Data 22:37 2021/8/16
          *@Param
          *@return
          */

        // 使用线程池        把更新操作 扔到线程池中 执行 和主线程 不相关
        threadService.updateArticleViewCount(articleMapper,article);
        return Result.success(articleVo);
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,false,false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor ,boolean isBody , boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,isBody,isCategory));
        }
        return articleVoList;
    }
    @Autowired
    private CategoryService categoryService;

    private  ArticleVo copy (Article article,boolean isTag ,boolean isAuthor ,boolean isBody , boolean isCategory){
        ArticleVo articleVo =new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH：mm"));
        //并不是所有接口都需要标签 作者信息
       if (isTag){
            Long articleId = article.getId();
            articleVo.setTags(tagService.findTagsByArtickleId(articleId));
        }
         if (isAuthor){
            Long authorId =article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }

        if (isAuthor){
            Long authorId =article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }
        if (isBody){
            Long bodyId = article.getBodyId();
            articleVo.setBody(findArticleBodyById(bodyId));
        }
        if(isCategory){
            Long categoryId = article.getCategoryId();
            articleVo.setCategory(categoryService.findCategoryById(categoryId));
        }
        return articleVo;
    }

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody articleBody = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(articleBody.getContent());
        return  articleBodyVo;
    }
}
