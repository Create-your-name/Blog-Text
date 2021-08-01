package com.SpringBootBlog.vo;
/*
import com.SpringBootBlog.dao.pojo.ArticleBody;
import com.SpringBootBlog.dao.pojo.Category;*/
import com.SpringBootBlog.dao.pojo.SysUser;
import com.SpringBootBlog.dao.pojo.Tag;
import lombok.Data;

import java.util.List;

@Data
public class ArticleVo {

    private Long id;

    private String title;

    private String summary;

    private int commentCounts;

    private int viewCounts;

    private int weight;
    /**
     * 创建时间
     */
    private String createDate;

    private String author;

/*    private ArticleBodyVo body;*/

    private List<TagVo> tags;

/*    private List<CategoryVo> categorys;*/

}