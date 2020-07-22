package com.aftvc.top.controller;


import com.aftvc.top.annotation.Log;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.domain.Song;
import com.aftvc.top.service.SongService;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
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
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@RestController
public class SongController {
    @Autowired
    private SongService songService;

    /**
     * 文件上传设置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //文件最大10M，DataUnit提供5中类型B,KB,MB,GB,TB
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        //设置总上传数据总大小为10M
        factory.setMaxRequestSize(DataSize.of(10, DataUnit.MEGABYTES));
        return factory.createMultipartConfig();
    }

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:D:\\Javastudy\\music-server\\img\\songPic\\");
            registry.addResourceHandler("/song/**").addResourceLocations("file:D:\\Javastudy\\music-server\\song\\");
        }
    }

    /**
     * 返回所有歌曲
     * @return
     */
    @GetMapping("/song")
    public Object allSong(){
        return songService.allSongs();
    }

    /**
     * 根据id查询song
     * @RequestParam id
     * @return
     */
    @GetMapping("/song/detail")
    public Object songOfId(@RequestParam("id") String id){
        return songService.songOfId(Integer.parseInt(id));
    }

    /**
     * 根据singId返回他的歌曲
     * @RequestParam singerId
     * @return
     */
    @GetMapping("/song/singer/detail")
    public Object songOfSongId(@RequestParam("singerId") String singerId){
        return songService.songOfSingerid(Integer.parseInt(singerId));
    }

    /**
     *根据singer姓名返回歌曲(模糊查询)
     * @RequestParam name
     * @return
     */
    @GetMapping("/song/singerName/detail")
    public Object songOfsingerName(@RequestParam("name") String name){
        return songService.songOfSingerName(name);
    }

    /**
     * 根据歌曲名称查询
     * @RequestParam name
     * @return
     */
    @GetMapping("/song/name/detail")
    public Object songOfName(@RequestParam("name") String name){
        return songService.songOfName(name);
    }

    /**
     * 根据song的id删除歌曲
     * @RequestParam id
     * @return
     */
    @Log("删除歌曲")
    @GetMapping("/song/delete")
    public ResponseBean deleteSongById(@RequestParam("id") String id){
        ResponseBean responseBean = new ResponseBean();
        int result = songService.deleteSongById(id);
        if(result>0){
            responseBean.setCode(1);
            responseBean.setMsg("删除成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("删除失败");
        }
        return responseBean;
    }

    /**
     * 歌曲上传
     * @RequestParam mpfile
     * @RequestParam singer_id
     * @RequestParam name
     * @RequestParam introduction
     * @RequestParam lyric
     * @return
     */
    @Log("上传歌曲")
    @PostMapping("/song/add")
    public Object addSong(@RequestParam("file") MultipartFile mpfile,@RequestParam("singerId") String singer_id,@RequestParam("name") String name,
                          @RequestParam("introduction") String introduction,@RequestParam("lyric") String lyric){
        String pic = "/img/songPic/tubiao.jpg";
        JSONObject jsonObject=new JSONObject();

        if(mpfile.isEmpty()){
            jsonObject.put("code",0);
            jsonObject.put("msg","音乐上传失败！");
        }
        String fileName=mpfile.getOriginalFilename();
        String filePath=System.getProperty("user.dir")+System.getProperty("file.separator")+"song";
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }
        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/"+fileName;
        try {
            mpfile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singer_id));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            boolean res = songService.addSong(song);
            if (res) {
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeUrlPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            } else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败" + e.getMessage());
            return jsonObject;
        } finally {
            return jsonObject;
        }

    }

    /**
     * 根据id更新歌曲

     * @return
     */
    @PostMapping("/song/update")
    public ResponseBean updateSongMsg(@RequestParam("id") String id,@RequestParam("singerId")String singer_id,
                                      @RequestParam("name")String name,@RequestParam("introduction") String introduction,
                                      @RequestParam("lyric")String lyric) {
        ResponseBean responseBean = new ResponseBean();
        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setSingerId(Integer.parseInt(singer_id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setUpdateTime(new Date());
        song.setLyric(lyric);
        boolean res = songService.updateSongMsg(song);
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
     * 更新song的图片
     * @param urlFile
     * @param id
     * @return
     */
    @PostMapping("/song/img/update")
    public JSONObject updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if (urlFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "音乐上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis()+urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/img/songPic/"+fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setPic(storeUrlPath);
            boolean res = songService.updateSongPic(song);
            if (res){
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeUrlPath);
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
    @PostMapping("/song/url/update")
    public Object updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id")int id) {
        JSONObject jsonObject = new JSONObject();

        if (urlFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "音乐上传失败！");
            return jsonObject;
        }
        String fileName = urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            boolean res = songService.updateSongUrl(song);
            if (res) {
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeUrlPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            } else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
                return jsonObject;
            }
        } catch (IOException e) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败" + e.getMessage());
            return jsonObject;
        } finally {
            return jsonObject;
        }
    }
}

