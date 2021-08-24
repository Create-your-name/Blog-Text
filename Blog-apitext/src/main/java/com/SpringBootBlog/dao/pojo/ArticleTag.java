package com.SpringBootBlog.dao.pojo;

import lombok.Data;
import sun.dc.pr.PRError;

@Data
public class ArticleTag {
    private  Long id ;
    private  Long articleId;
    private  Long tagId;
}
