package com.SpringBootBlog.service;

import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.TagVo;

import java.util.List;

public interface TagService {
    List<TagVo> findTagsByArtickleId (Long articleId);

    Result hots(int limit);
}
