package com.SpringBootBlog.dao.mapper;

import com.SpringBootBlog.dao.dos.Archives;
import com.SpringBootBlog.dao.pojo.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface ArticleMapper extends BaseMapper<Article> {

    List<Archives> listArchives();
}
