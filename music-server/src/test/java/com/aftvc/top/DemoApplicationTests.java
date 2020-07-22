package com.aftvc.top;

import com.aftvc.top.dao.AdminMapper;
import com.aftvc.top.dao.ConsumerMapper;
import com.aftvc.top.dao.RankMapper;
import com.aftvc.top.dao.SystemLogMapper;
import com.aftvc.top.domain.Admin;
import com.aftvc.top.domain.Consumer;
import com.aftvc.top.domain.Rank;
import com.aftvc.top.domain.SystemLog;
import com.aftvc.top.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Map;

@SpringBootTest
class DemoApplicationTests {
    /*@Autowired
    private AdminMapper adminMapper;
    @Test
    void contextLoads() {
        int admin = adminMapper.veritypasswd("admin", "123");
        if(admin>0){
            System.out.println("登录");
        }else{
            System.out.println("失败");
        }
    }*/
    @Autowired
    private ConsumerMapper consumerMapper;
    @Test
    void contextLoads() {
        List<Consumer> consumers = consumerMapper.selectList(null);
        System.out.println(consumers);
    }

   @Autowired
   private SystemLogMapper systemLogMapper;
    @Test
    void LogTest(){
        /*String localhost="127.0.0.1";
        SystemLog systemLog = new SystemLog();
        systemLog.setCreatetime(new Date());
        systemLog.setUsername("mylove");
        systemLog.setOperation("管理员通过密码登录");
        systemLog.setTime(784);
        systemLog.setIp(localhost);
        systemLog.setMethod("com.liznsalt.javatask.takeout.common.controller.Test.methodOne()");
        systemLog.setParams("  username: mai  password: 12345678");

        systemLogMapper.saveSysLog(systemLog);*/
        List<SystemLog> maps = systemLogMapper.queryAllLogs();
        for (SystemLog map : maps) {
            System.out.println(map);
        }
    }

    @Autowired
    private RedisUtil redisUtil;
    @Test
    void test(){
        Admin admin = new Admin();
        admin.setName("admin");
        admin.setPassword("123124124");
        System.out.println(redisUtil.set("name", admin));
    }


}
