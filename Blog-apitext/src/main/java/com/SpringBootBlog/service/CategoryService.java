package com.SpringBootBlog.service;

import com.SpringBootBlog.vo.CategoryVo;
import com.SpringBootBlog.vo.Result;

public interface CategoryService {

    CategoryVo findCategoryById(Long categoryId);

    Result findAll();

    Result findAllDetail();

    Result categoryDetailById(Long id);
}
