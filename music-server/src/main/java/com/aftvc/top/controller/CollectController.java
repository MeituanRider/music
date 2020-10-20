package com.aftvc.top.controller;


import com.aftvc.top.annotation.Log;
import com.aftvc.top.domain.Collect;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.service.impl.CollectServiceImpl;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

    /**
     *  返回所有用户收藏列表
     * @return
     */
    @GetMapping("/collection")
    public Object allCollection(){
        return collectService.allCollect();
    }

    /**
     *返回的指定用户ID收藏列表
     * @param userId
     * @return
     */
    @GetMapping("/collection/detail")
    public Object collectionOfUser(@RequestParam("userId")String userId){
        return collectService.collectionOfUser(Integer.parseInt(userId));
    }

    /**
     * 增加收藏列表
     * @param user_id
     * @param type
     * @param song_id
     * @return
     */
    @PostMapping("/collection/add")
    public ResponseBean addCollection(@RequestParam("userId")String user_id, @RequestParam("type")String type, @RequestParam("songId")String song_id, HttpServletRequest request){
        ResponseBean responseBean = new ResponseBean();
        Collect collect = new Collect();
        String song_list_id=request.getParameter("songListId");
        System.out.println(1);
        if (song_id == ""){
            responseBean.setCode(0);
            responseBean.setMsg("收藏歌曲为空");
            System.out.println("收藏歌曲为空");
        }else if (collectService.existSongId(Integer.parseInt(user_id), Integer.parseInt(song_id))){
            responseBean.setCode(2);
            responseBean.setMsg("已收藏");
            System.out.println("已收藏");
        }
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));

        if (new Byte(type) == 0) {
            collect.setSongId(Integer.parseInt(song_id));
        } else if (new Byte(type) == 1) {
            collect.setSongListId(Integer.parseInt(song_list_id));
        }

        collect.setCreateTime(new Date());
        boolean res = collectService.addCollection(collect);
        if(res){
            responseBean.setCode(1);
            responseBean.setMsg("收藏成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("收藏失败");
        }
        return responseBean;
    }

    /**
     * 跟新收藏
     * @param id
     * @param user_id
     * @param type
     * @param song_id
     * @return
     */
    @PostMapping("/collection/update")
    public ResponseBean updateCollectMsg(@RequestParam("id")String id,@RequestParam("userId")String user_id,@RequestParam("type")String type,@RequestParam("songId")String song_id){
        ResponseBean responseBean = new ResponseBean();
        Collect collect=new Collect();
        collect.setId(Integer.parseInt(id));
        collect.setUserId(Integer.parseInt(user_id));
        collect.setType(new Byte(type));
        collect.setSongId(Integer.parseInt(song_id));

        boolean result = collectService.updateCollectMsg(collect);
        if(result){
            responseBean.setCode(1);
            responseBean.setMsg("修改成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("修改失败");
        }
        return responseBean;
    }

    /**
     * 根据user_id song_id删除歌单
     * @param user_id
     * @param song_id
     * @return
     */
    @GetMapping("/collection/delete")
    public Object deleteCollection(@RequestParam("userId")String user_id,@RequestParam("songId")String song_id){
        return collectService.deleteCollect(Integer.parseInt(user_id), Integer.parseInt(song_id));
    }
}

