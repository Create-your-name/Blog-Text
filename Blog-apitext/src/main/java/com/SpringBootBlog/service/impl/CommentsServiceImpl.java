package com.SpringBootBlog.service.impl;

import com.SpringBootBlog.dao.mapper.CommentMapper;
import com.SpringBootBlog.dao.pojo.Comment;
import com.SpringBootBlog.service.CommentsService;
import com.SpringBootBlog.service.SysUserService;
import com.SpringBootBlog.vo.CommentVo;
import com.SpringBootBlog.vo.Result;
import com.SpringBootBlog.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService  {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public Result commentsByArticleId(Long id) {
        /**
          * 1.根据文章id  查询评论列表  从 comment 表中查询
         *  2. 根据作者id 查询 作者信息
         *  3. 判断 如何 level = 1 要去查询 有没有子评论
         *  4. 如果有 根据评论id  去查询  parent——id
          *@Author 刘海
          *@Data 22:47 2021/8/17
          *@Param
          *@return
          */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId ,id);
        queryWrapper.eq(Comment::getLevel,1);
        List<Comment> comments =commentMapper.selectList(queryWrapper);
        List<CommentVo> commentVoList =copyList(comments);

        return Result.success(commentVoList);
    }

    private List<CommentVo> copyList(List<Comment> comments) {
        List<CommentVo> commentVoList =new ArrayList<>();
        for(Comment comment : comments){
            commentVoList.add((copy(comment)));
        }
        return commentVoList;
    }

    @Autowired
    private SysUserService  sysUserService;

    private CommentVo copy(Comment comment) {
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        //  作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVo = this.sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);
        Integer level = comment.getLevel();

        if(1 == level){
            Long id = comment.getId();
            List<CommentVo> commentVoList =findCommentsByParentId(id);
            commentVo.setChildrens(commentVoList);
        }
        if (level > 1) {
            Long toUid = comment.getToUid();
            UserVo toUserVo = this.sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;

    }

    private List<CommentVo> findCommentsByParentId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getParentId,id);
        queryWrapper.eq(Comment::getLevel,2);
        return  copyList(commentMapper.selectList(queryWrapper));
    }
}
