package com.aftvc.top.controller;


import com.aftvc.top.annotation.Log;
import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.service.AdminService;
import com.aftvc.top.utils.MD5Utils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Log("管理员登录")
    @PostMapping("/login/status")
    public ResponseBean loginStatus(HttpServletRequest request, HttpSession session, @RequestParam("name") String name, @RequestParam("password") String password){
        ResponseBean responseBean=new ResponseBean();
        MD5Utils md5Utils = new MD5Utils();
        boolean result = adminService.veritypasswd(name, md5Utils.code(password));
        if(result){
            responseBean.setCode(1);
            responseBean.setMsg("登录成功");
            session.setAttribute("name", name);
        }else{
            responseBean.setCode(0);
            responseBean.setMsg("用户名或密码错误");
        }
        return responseBean;
    }

}

