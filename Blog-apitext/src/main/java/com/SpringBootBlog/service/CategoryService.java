package com.SpringBootBlog.service;

import com.SpringBootBlog.vo.CategoryVo;

public interface CategoryService {

    CategoryVo findCategoryById(Long categoryId);
}
