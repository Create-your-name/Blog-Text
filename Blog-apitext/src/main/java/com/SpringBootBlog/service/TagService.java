package com.SpringBootBlog.service;

import com.SpringBootBlog.vo.TagVo;

import java.util.List;

public interface TagService {
    List<TagVo> findTagsByArtickleId (Long articleId);
}
