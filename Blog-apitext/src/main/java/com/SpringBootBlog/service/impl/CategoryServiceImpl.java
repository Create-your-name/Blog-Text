package com.SpringBootBlog.service.impl;

import com.SpringBootBlog.dao.mapper.CategoryMapper;
import com.SpringBootBlog.dao.pojo.Category;
import com.SpringBootBlog.service.CategoryService;
import com.SpringBootBlog.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.rowset.CachedRowSet;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public CategoryVo findCategoryById(Long categoryId) {
        Category category = categoryMapper.selectById(categoryId);
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        return categoryVo;
    }
}
