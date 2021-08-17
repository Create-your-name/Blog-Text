package com.SpringBootBlog.dao.pojo;

import lombok.Data;

@Data
public class Comment {
    private  Long id ;

    private String content;

    private  Long createDate;

    private  Long articId;

    private  Long authorId;

    private  Long parentId;

    private  Long toUid;

    private  Integer level;
}
