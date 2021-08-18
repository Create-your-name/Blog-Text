package com.SpringBootBlog.service;

import com.SpringBootBlog.vo.Result;

public interface CommentsService {
    /**
      *     根据文章id 查询 所有评论列表
      *@Author 刘海
      *@Data 22:46 2021/8/17
      *@Param
      *@return
      */
    Result commentsByArticleId(Long id);

}
