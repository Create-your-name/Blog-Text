package com.SpringBootBlog.dao.pojo;

import lombok.Data;

@Data
public class Article {

    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;

    private Long id;

    private String title;

    private String summary;

    /**
      *     pojo 和 数据映射的 不能出现基本类型的操作
     *      必须 要更新为封装类型 要不然 会 更新不成功
      *@Author 刘海
      *@Data 22:28 2021/8/17
      *@Param
      *@return
      */
    private Integer commentCounts;

    private Integer viewCounts;

    /**
     * 作者id
     */
    private Long authorId;
    /**
     * 内容id
     */
    private Long bodyId;
    /**
     *类别id
     */
    private Long categoryId;

    /**
     * 置顶
     */
    /**
      *     pojo 和 数据映射的 不能出现基本类型的操作
     *       必须 要更新为封装类型 要不然 会 更新不成功
      *@Author 刘海
      *@Data 22:30 2021/8/17
      *@Param
      *@return
      */
    private Integer weight;


    /**
     * 创建时间
     */
    private Long createDate;
}