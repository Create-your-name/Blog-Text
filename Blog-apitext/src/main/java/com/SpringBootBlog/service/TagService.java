package com.SpringBootBlog.service;

import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.TagVo;

import java.util.List;

public interface TagService {
    List<TagVo> findTagsByArtickleId (Long articleId);

    Result hots(int limit);

    /**
      * 查询文章标签
      *@Author 刘海
      *@Data 14:09 2021/8/24
      *@Param
      *@return
      */
    Result findAll();

    Result findAllDetail();

    Result findAllDetailById(Long id);
}
