
package com.SpringBootBlog.dao.mapper;

import com.SpringBootBlog.dao.pojo.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {

/**
      *根据文章id  查询 标签列表
      *@Author 刘海
      *@Data 22:16 2021/8/4
      *@Param
      *@return
      */

    List<Tag> findTagsByArticleId (Long articleId);

    /**
      * 查询最热标签
      *@Author 刘海
      *@Data 22:39 2021/8/9
      *@Param
      *@return
      */
    List<Long> findHotsTagIds(int limit);

    List<Tag> findTagsByTagIds(List<Long> tagIds);
}

