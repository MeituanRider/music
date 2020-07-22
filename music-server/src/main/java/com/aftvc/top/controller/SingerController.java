package com.aftvc.top.controller;


import com.aftvc.top.annotation.Log;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.domain.Singer;
import com.aftvc.top.service.SingerService;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
public class SingerController {
    @Autowired
    private SingerService singerService;

    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/img/singerPic/**").addResourceLocations("file:D:\\Javastudy\\music-server\\img\\singerPic\\");
        }
    }

    /**
     * 返回所有歌手信息
     * @return
     */
    @GetMapping("/singer")
    public Object allSinger(){
        return singerService.allSinger();
    }

    /**
     * 歌手的添加
     * @RequestParam name
     * @RequestParam sex
     * @RequestParam pic
     * @RequestParam birth
     * @RequestParam location
     * @RequestParam introduction
     * @return
     */
    @Log("添加歌手")
    @PostMapping("/singer/add")
    public ResponseBean addSinger(@RequestParam("name") String name,@RequestParam("sex") String sex,@RequestParam("pic") String pic,
                                  @RequestParam("birth") String birth,@RequestParam("location") String location,@RequestParam("introduction") String introduction){
        ResponseBean responseBean = new ResponseBean();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        }catch (Exception e){
            e.printStackTrace();
        }
        Singer singer = new Singer();
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(myBirth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        int result = singerService.addSinger(singer);
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
     * 模糊查询，根据姓名
     * @RequestParam name
     * @return
     */
    @GetMapping("/singer/name/detail")
    public Object singerOfName(@RequestParam("name") String name){
        return singerService.singerOfName(name);
    }

    /**
     * 根据性别查找歌手
     * @RequestParam sex
     * @return
     */
    @GetMapping("/singer/sex/detail")
    public Object singerOfSex(@RequestParam("sex") String sex){
        return singerService.singerOfSex(sex);
    }

    /**
     * 删除singer根据id
     * @RequestParam id
     * @return
     */
    @Log("删除歌手")
    @GetMapping("/singer/delete")
    public ResponseBean deleteSinger(@RequestParam("id") String id){
        ResponseBean responseBean = new ResponseBean();
        int result = singerService.deleteSinger(Integer.parseInt(id));
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
     * 根据id修改更新singer
     * @RequestParam id
     * @RequestParam name
     * @RequestParam sex
     * @RequestParam pic
     * @RequestParam birth
     * @RequestParam location
     * @RequestParam introduction
     * @return
     */
    @PostMapping("/singer/update")
    public ResponseBean updateSinger(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("sex") String sex,@RequestParam("pic") String pic,
                                     @RequestParam("birth") String birth,@RequestParam("location") String location,@RequestParam("introduction") String introduction){
        ResponseBean responseBean = new ResponseBean();
        Singer singer = new Singer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date mybirth=new Date();
        try {
            mybirth=dateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setPic(pic);
        singer.setBirth(mybirth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        int result = singerService.updateSinger(singer);
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
     * 更新头像Byid
     * @RequestParam avatorFile
     * @RequestParam id
     * @return
     */
    @PostMapping("/singer/avatar/update")
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id) {
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean res=singerService.updateSingerPic(singer);
            if (res) {
                jsonObject.put("code", 1);
                jsonObject.put("pic", storeAvatorPath);
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

