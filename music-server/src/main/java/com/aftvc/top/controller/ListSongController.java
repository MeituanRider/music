package com.aftvc.top.controller;


import com.aftvc.top.annotation.Log;
import com.aftvc.top.domain.ListSong;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.service.impl.ListSongServiceImpl;
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
public class ListSongController {

    @Autowired
    private ListSongServiceImpl listSongService;

    /**
     *返回歌单里包含的所有歌曲
     * @return
     */
    @GetMapping("/listSong")
    public Object allListSong(){
        return listSongService.allListSong();
    }

    /**
     * 返回歌单里指定歌单ID的歌曲
     * @param songListId
     * @return
     */
    @GetMapping("/listSong/detail")
    public Object listSongOfSongId(@RequestParam("songListId")String songListId){
        return listSongService.listSongOfSongId(Integer.parseInt(songListId));
    }

    /**
     * 给歌单添加歌曲
     * @param songId
     * @param songListId
     * @return
     */
    @Log("添加歌曲")
    @PostMapping("/ListSong/add")
    public ResponseBean addListSong(@RequestParam("songId")String songId,@RequestParam("songListId")String songListId){
        ResponseBean responseBean=new ResponseBean();
        ListSong listSong = new ListSong();
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));
        int result = listSongService.addListSong(listSong);
        if(result>0){
            responseBean.setCode(1);
            responseBean.setMsg("添加成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("添加失败");
        }
        return responseBean;

    }

    /**
     * 更新歌单里面的歌曲信息
     * @param id
     * @param songId
     * @param songListId
     * @return
     */
    @PostMapping("/listSong/update")
    public ResponseBean updateListSongMsg(@RequestParam("id")String id,@RequestParam("songId")String songId,@RequestParam("songListId")String songListId){
        ResponseBean responseBean = new ResponseBean();
        ListSong listSong=new ListSong();
        listSong.setId(Integer.parseInt(id));
        listSong.setSongId(Integer.parseInt(songId));
        listSong.setSongListId(Integer.parseInt(songListId));

        boolean result = listSongService.updateListSongMsg(listSong);
        if(result){
            responseBean.setCode(1);
            responseBean.setMsg("修改成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("修改失败");
        }
        return  responseBean;

    }

    /**
     * 删除歌单里的歌曲
     * @param songId
     * @return
     */
    @Log("删除歌曲")
    @GetMapping("/ListSong/delete")
    public Object deleteListSong(@RequestParam("songId")String songId){
        return listSongService.deleteListSong(Integer.parseInt(songId));
    }


}

