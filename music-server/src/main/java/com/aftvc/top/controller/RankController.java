package com.aftvc.top.controller;


import com.aftvc.top.domain.Ranks;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@RestController
public class RankController {
    @Autowired
    private RankServiceImpl rankService;

    /**
     * 获取指定歌单的评分
     * @param songListId
     * @return
     */
    @GetMapping("/rank")
    public Object rankOfSongListId(@RequestParam("songListId")String songListId){
        return rankService.rankOfSongListId(Long.parseLong(songListId));
    }

    /**
     * 添加评分
     * @param songListId
     * @param consumerId
     * @param score
     * @return
     */
    @PostMapping("/rank/add")
    public ResponseBean addRank(@RequestParam("songListId") String songListId,@RequestParam("consumerId")String consumerId,@RequestParam("score")String score){
        ResponseBean responseBean = new ResponseBean();
        Ranks ranks =new Ranks();
        ranks.setSongListId(Long.parseLong(songListId));
        ranks.setConsumerId(Long.parseLong(consumerId));
        ranks.setScore(Integer.parseInt(score));
        int i = rankService.addRank(ranks);
        if(i>0){
            responseBean.setCode(1);
            responseBean.setMsg("评价成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("评价失败");
        }
        return responseBean;
    }
}

