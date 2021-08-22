package com.SpringBootBlog.vo.params;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class CommentParam {
    private  Long articleId ;

    private  String content;

    private  Long parent;

    private  Long toUserId;
}
