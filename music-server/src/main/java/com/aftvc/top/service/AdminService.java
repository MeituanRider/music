package com.aftvc.top.service;

import com.aftvc.top.domain.Admins;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
public interface AdminService extends IService<Admins> {
    boolean veritypasswd(String name,String password);
}
