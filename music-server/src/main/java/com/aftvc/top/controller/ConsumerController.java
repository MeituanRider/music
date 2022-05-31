package com.aftvc.top.controller;


import com.aftvc.top.annotation.Log;
import com.aftvc.top.domain.Consumer;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.service.ConsumerService;
import com.aftvc.top.utils.MD5Utils;
import com.aftvc.top.utils.RedisUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.File;
import java.io.FileNotFoundException;
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
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private RedisUtil redisUtil;


    @Configuration
    public class MyPicConfig implements WebMvcConfigurer {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/avatorImages/**").addResourceLocations("file:D:\\Javastudy\\music-server\\avatorImages\\");
           // registry.addResourceHandler("/avatorImages/**").addResourceLocations("file:/Auserabled/java/avatorImages");
        }
    }

    /**
     * 判断用户是否存在
     * @param request
     * @return
     */
    @PostMapping("/isOnly")
    public ResponseBean isOnly(HttpServletRequest request){
        String username = request.getParameter("username");
        ResponseBean responseBean = new ResponseBean();
        boolean only = consumerService.isOnly(username);
        if(only){
            responseBean.setCode(1);
            responseBean.setMsg("用户名已存在!");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("可以注册!");
        }
        return responseBean;
    }

    /**
     * 添加用户
     * @RequestParam username
     * @RequestParam password
     * @RequestParam sex
     * @RequestParam phone_num
     * @RequestParam email
     * @RequestParam birth
     * @RequestParam introduction
     * @RequestParam location
     * @RequestParam avator
     * @return
     */
    @Log("新用户注册")
    @PostMapping("/user/add")
    public ResponseBean addUser(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("sex") String sex,
                                @RequestParam("phone_num") String phone_num,@RequestParam("email") String email,@RequestParam("birth") String birth,
                                @RequestParam("introduction") String introduction,@RequestParam("location") String location,@RequestParam("avator") String avator){
        ResponseBean responseBean=new ResponseBean();
        MD5Utils md5Utils = new MD5Utils();
        Consumer consumer = new Consumer();

        if(username.equals("")||username==null||password.equals("")||password==null){
            responseBean.setCode(0);
            responseBean.setMsg("用户名或密码错误");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();

        try {
            myBirth= simpleDateFormat.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        consumer.setUsername(username);
        consumer.setPassword(md5Utils.code(password));
        consumer.setSex(new Byte(sex));
        if (phone_num == "") {
            consumer.setPhoneNum(null);
        } else{
            consumer.setPhoneNum(phone_num);
        }

        if (email == "") {
            consumer.setEmail(null);
        } else{
            consumer.setEmail(email);
        }
        consumer.setBirth(myBirth);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setAvator(avator);
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());

        boolean result = consumerService.addUser(consumer);
        if (result){
            responseBean.setCode(1);
            responseBean.setMsg("注册成功");
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("注册失败");
            responseBean.setMsg("注册失败");
        }
        return responseBean;
    }

    /**
     *判断用户是否登录成功
     * @RequestParam username
     * @RequestParam password
     * @RequestParam session
     * @return
     */
    @Log("普通用户登录")
    @PostMapping("/user/login/status")
    public ResponseBean loginStatus(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        ResponseBean responseBean=new ResponseBean();
        MD5Utils md5Utils = new MD5Utils();
        boolean result = consumerService.verifyPassword(username, md5Utils.code(password));
        if(result){
            responseBean.setCode(1);
            responseBean.setMsg("登录成功");
            responseBean.setUserMsg(consumerService.loginStatus(username));
            /*session.setAttribute("username",username);*/
            redisUtil.set("username",username);
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("登录失败");
        }
        return responseBean;
    }

    /**
     * 返回所有用户数据
     * @return
     */
    @GetMapping("/user")
    public List<Consumer> allUser(){
        return consumerService.allUser();
    }

    /**
     * 根据id返回用户信息
     * @RequestParam id
     * @return
     */
    @GetMapping("/user/detail")
    public List<Consumer> userOfId(@RequestParam("id") String id){
        return consumerService.UserOfId(Integer.parseInt(id));
    }

    /**
     * 根据id删除用户信息
     * @RequestParam id
     * @return
     */
    @Log("删除用户操作")
    @GetMapping("/user/delete")
    public Object deleteUserById(@RequestParam("id") String id){
        boolean result = consumerService.deleteUserById(Integer.parseInt(id));
        return result;
    }

    /**
     * 根据id修改用户信息
     * @RequestParam id
     * @RequestParam username
     * @RequestParam password
     * @RequestParam sex
     * @RequestParam phone_num
     * @RequestParam email
     * @RequestParam birth
     * @RequestParam introduction
     * @RequestParam location
     * @return
     */
    @PostMapping("/user/update")
    public ResponseBean updateUserMsg(@RequestParam("id") String id,@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("sex") String sex,
                                @RequestParam("phone_num") String phone_num,@RequestParam("email") String email,@RequestParam("birth") String birth,
                                @RequestParam("introduction") String introduction,@RequestParam("location") String location){
        ResponseBean responseBean = new ResponseBean();
        if (username.equals("") || username == null||password==null||password.equals("")){
            responseBean.setCode(0);
            responseBean.setMsg("用户名或密码错误");
        }

        Consumer consumer = new Consumer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        }catch (Exception e){
            e.printStackTrace();
        }
        consumer.setId(Integer.parseInt(id));
        consumer.setUsername(username);
        consumer.setPassword(password);
        consumer.setSex(new Byte(sex));
        consumer.setPhoneNum(phone_num);
        consumer.setEmail(email);
        consumer.setBirth(myBirth);
        consumer.setIntroduction(introduction);
        consumer.setLocation(location);
        consumer.setUpdateTime(new Date());
        int result = consumerService.updateUserMsg(consumer);
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
     * 根据id更改用户头像
     * @RequestParam avatorFile
     * @RequestParam id
     * @return
     */
    @PostMapping("/user/avatar/update")
    public Object updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();
        if(avatorFile.isEmpty()){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
        }
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src/main/resources/static/avatorImages" ;
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/static/avatorImages/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean res = consumerService.updateUserAvator(consumer);
            if (res){
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeAvatorPath);
                jsonObject.put("msg", "上传成功");
            }else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
            }
        }catch (IOException e){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }

}

