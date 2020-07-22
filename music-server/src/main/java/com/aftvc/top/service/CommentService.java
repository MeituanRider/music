package com.aftvc.top.service;

import com.aftvc.top.domain.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
public interface CommentService extends IService<Comment> {

    List<Comment> allComment();

    List<Comment> commentOfSongId(int songId);

    List<Comment> commentOfSongListId(int songListId);

    boolean addComment(Comment comment);

    boolean updateCommentMsg(Comment comment);

    boolean deleteComment(int id);
}
