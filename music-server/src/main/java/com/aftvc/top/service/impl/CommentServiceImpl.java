package com.aftvc.top.service.impl;

import com.aftvc.top.domain.Comment;
import com.aftvc.top.dao.CommentMapper;
import com.aftvc.top.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Override
    public List<Comment> allComment() {
        return commentMapper.selectList(null);
    }

    @Override
    public List<Comment> commentOfSongId(int songId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_id",songId);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public List<Comment> commentOfSongListId(int songListId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id",songListId);
        return commentMapper.selectList(wrapper);
    }

    @Override
    public boolean addComment(Comment comment) {
        return commentMapper.insert(comment)>0?true:false;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        return commentMapper.updateById(comment)>0?true:false;
    }

    @Override
    public boolean deleteComment(int id) {
        return commentMapper.deleteById(id)>0?true:false;
    }
}
