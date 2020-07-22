package com.aftvc.top.service;

import com.aftvc.top.domain.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
public interface AdminService extends IService<Admin> {
    boolean veritypasswd(String name,String password);
}
