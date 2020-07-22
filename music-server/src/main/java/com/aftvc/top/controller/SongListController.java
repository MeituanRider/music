package com.aftvc.top.controller;


import com.aftvc.top.annotation.Log;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.domain.SongList;
import com.aftvc.top.service.SongService;
import com.aftvc.top.service.impl.SongListServiceImpl;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@RestController
public class SongListController {
    @Autowired
    private SongListServiceImpl songListService;


    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:D:\\Javastudy\\music-server\\img\\songListPic\\");
        }
    }

    /**
     * 添加歌单
     * @param title
     * @param pic
     * @param introduction
     * @param style
     * @return
     */
    @Log("新增歌单")
    @PostMapping("/songList/add")
    public ResponseBean addSongList(@RequestParam("title") String title,@RequestParam("pic")String pic,@RequestParam("introduction")String introduction,@RequestParam("style")String style){
        ResponseBean responseBean=new ResponseBean();
        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);

        boolean result = songListService.addSongList(songList);
        if(result){
            responseBean.setCode(1);
            responseBean.setMsg("添加成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("添加失败");
        }
        return responseBean;
    }

    /**
     * 返回所有歌单
     * @return
     */
    @GetMapping("/songList")
    public Object allSongList(){
        return songListService.allSongList();
    }

    /**
     * 根据title返回歌单
     * @param title
     * @return
     */
    @GetMapping("/songList/title/detail")
    public Object songListOfTitle(@RequestParam("title") String title){
        return songListService.songListOfTitle(title);
    }

    /**
     * 作用同上，主要对接前端接口
     * @param title
     * @return
     */
    @GetMapping("/songList/likeTitle/detail")
    public Object songListLikeTitle(@RequestParam("title") String title){
        return songListService.songListOfTitle(title);
    }

    /**
     * 根据style返回歌单
     * @param style
     * @return
     */
    @GetMapping("/songList/style/detail")
    public Object songListOfStyle(@RequestParam("style") String style){
        return songListService.songListOfStyle(style);
    }

    /**
     *更新歌单
     * @param id
     * @param title
     * @param pic
     * @param introduction
     * @param style
     * @return
     */
    @PostMapping("/songList/update")
    public ResponseBean updateSongList(@RequestParam("id")String id,@RequestParam("title")String title,@RequestParam("pic")String pic,
                                       @RequestParam("introduction")String introduction,@RequestParam("style")String style){
        ResponseBean responseBean=new ResponseBean();
        SongList songList=new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setPic(pic);
        songList.setIntroduction(introduction);
        songList.setStyle(style);

        int result = songListService.updateSongList(songList);
        if(result>0){
            responseBean.setCode(1);
            responseBean.setMsg("修改成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("修改失败");
        }
        return responseBean;

    }

    /**
     * 根据id删除歌单
     * @param id
     * @return
     */
    @Log("删除歌单")
    @GetMapping("/songList/delete")
    public Object deleteSongList(@RequestParam("id") String id){
        return songListService.deleteSongList(Integer.parseInt(id));
    }

    /**
     * 歌单更新图片
     * @param avatorFile
     * @param id
     * @return
     */
    @PostMapping("/songList/img/update")
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic" ;
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/songListPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean res = songListService.updateSongListImg(songList);
            if (res){
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeAvatorPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            }else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
                return jsonObject;
            }
        }catch (IOException e){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败" + e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

}

