package com.aftvc.top.controller;


import com.aftvc.top.domain.Comment;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@RestController
public class CommentController {

    @Autowired
    private CommentServiceImpl commentService;

    /**
     * 返回所有评论
     * @return
     */
    @GetMapping("/comment")
    public Object allComment(){
        return commentService.allComment();
    }

    /**
     * 根据songId返回所有
     * @param songId
     * @return
     */
    @GetMapping("/comment/song/detail")
    public Object commentOfSongId(@RequestParam("songId")String songId){
        return commentService.commentOfSongId(Integer.parseInt(songId));
    }

    /**
     * 根据songListId返回所有
     * @param songListId
     * @return
     */
    @GetMapping("/comment/songList/detail")
    public Object commentOfSongListId(@RequestParam("songListId")String songListId){
        return commentService.commentOfSongListId(Integer.parseInt(songListId));
    }

    /**
     * 增加评论
     * @param userId
     * @param type
     * @param songListId
     * @param songId
     * @param content
     * @return
     */
    @PostMapping("/comment/add")
    public ResponseBean addComment(@RequestParam("userId")String userId,@RequestParam("type")String type,@RequestParam("songListId")String songListId,
                                   @RequestParam("songId")String songId,@RequestParam("content")String content){
        ResponseBean responseBean = new ResponseBean();
        Comment comment = new Comment();

        comment.setUserId(Integer.parseInt(userId));
        comment.setType(new Byte(type));
        if (new Byte(type) == 0) {
            comment.setSongId(Integer.parseInt(songId));
        } else if (new Byte(type) == 1) {
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        comment.setCreateTime(new Date());
        boolean res = commentService.addComment(comment);
        if (res){
            responseBean.setCode(1);
            responseBean.setMsg("评论成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("评论失败");
        }
        return responseBean;
    }

    /**
     * 给评论点赞
     * @param id
     * @param up
     * @return
     */
    @PostMapping("/comment/like")
    public ResponseBean commentOfLike(@RequestParam("id")String id,@RequestParam("up")String up){
        ResponseBean responseBean = new ResponseBean();
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUp(Integer.parseInt(up));

        boolean res = commentService.updateCommentMsg(comment);
        if(res){
            responseBean.setCode(1);
            responseBean.setMsg("点赞成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("点赞失败");
        }
        return responseBean;
    }

    /**
     * 更新评论
     * @param id
     * @param userId
     * @param songId
     * @param songListId
     * @param content
     * @param type
     * @param up
     * @return
     */
    @PostMapping("/comment/update")
    public ResponseBean updateCommentMsg(@RequestParam("id")String id,@RequestParam("userId")String userId,@RequestParam("songId")String songId,@RequestParam("songListId")String songListId,
                                         @RequestParam("content")String content,@RequestParam("type")String type,@RequestParam("up")String up){
        ResponseBean responseBean = new ResponseBean();
        Comment comment=new Comment();
        comment.setId(Integer.parseInt(id));
        comment.setUserId(Integer.parseInt(userId));
        if(songId==""){
            comment.setSongId(null);
        }else{
            comment.setSongId(Integer.parseInt(songId));
        }

        if(songListId==""){
            comment.setSongListId(null);
        }else{
            comment.setSongListId(Integer.parseInt(songListId));
        }
        comment.setContent(content);
        comment.setType(new Byte(type));
        comment.setUp(Integer.parseInt(up));

        boolean res = commentService.updateCommentMsg(comment);

        if(res){
            responseBean.setCode(1);
            responseBean.setMsg("修改成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("修改失败");
        }
        return responseBean;
    }

    /**
     * 删除评论
     * @param id
     * @return
     */
    @GetMapping("/comment/delete")
    public Object deleteComment(@RequestParam("id")String id){
        return commentService.deleteComment(Integer.parseInt(id));
    }
}

