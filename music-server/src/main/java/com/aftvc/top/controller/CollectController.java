package com.aftvc.top.controller;


import com.aftvc.top.annotation.Log;
import com.aftvc.top.domain.Collect;
import com.aftvc.top.domain.Consumer;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.service.ConsumerService;
import com.aftvc.top.service.impl.CollectServiceImpl;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

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

    @Resource
    private ConsumerService consumerService;

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
     * @return
     */
    @PostMapping("/collection/add")
    public ResponseBean addCollection(@RequestParam("userId")String userId, @RequestParam("type")String type, @RequestParam("songId")String songId){
        ResponseBean responseBean = new ResponseBean();
        Collect collect = new Collect();
        Consumer exitConsumer = consumerService.getById(userId);
        //收藏
        //查询是否存在
        Collect collectExit=collectService.selectCollection(userId,songId);
        //userId 和 songId只要一个为空 返回空
        if(null ==exitConsumer){
            responseBean.setCode(0);
            responseBean.setMsg("用户不存在");
        }
        if(songId ==null || "".equals(songId)){
            responseBean.setCode(0);
            responseBean.setMsg("收藏歌曲为空");
        }else if(collectExit!=null){//删除收藏
            collectService.deleteCollect(Integer.parseInt(userId),Integer.parseInt(songId));
            responseBean.setCode(2);
            responseBean.setMsg("取消收藏");
        }else{//添加收藏
            collect.setSongId(Integer.parseInt(songId));
            collect.setType(Byte.valueOf(type));
            collect.setUserId(Integer.parseInt(userId));
            collect.setCreateTime(new Date());
            responseBean.setMsg("收藏歌曲成功");
            responseBean.setCode(1);
            collectService.save(collect);
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

