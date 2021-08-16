package com.SpringBootBlog.service;

import com.SpringBootBlog.dao.mapper.ArticleMapper;
import com.SpringBootBlog.dao.pojo.Article;
import com.SpringBootBlog.vo.ArticleBodyVo;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import lombok.val;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {
        //  希望 操作在线程池 执行 不会影响 主线程
    @Async ("taskExecutor")     // 声明这是子线程 去执行的 方法
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        int viewCounts = article.getViewCounts();
        Article articleUpdate =new Article();
        articleUpdate.setViewCounts(viewCounts +1);
        LambdaUpdateWrapper<Article> updateWrapper =new LambdaUpdateWrapper<>();
        updateWrapper.eq(Article::getId,article.getId());
        // 设置一个 为了在多线程环境下 线程安全
        updateWrapper.eq(Article::getViewCounts,viewCounts);
        //update article set view——conunt=100 where view——count=99 and id= 11
        articleMapper.update(articleUpdate,updateWrapper);
        try {
            Thread.sleep( 5000);
            System.out.println("更新完成。。。 ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
