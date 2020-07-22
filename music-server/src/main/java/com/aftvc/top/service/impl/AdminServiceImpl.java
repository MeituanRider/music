package com.aftvc.top.service.impl;

import com.aftvc.top.domain.Admin;
import com.aftvc.top.dao.AdminMapper;
import com.aftvc.top.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@Service
@Transactional
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Override
    public boolean veritypasswd(String name, String password) {
        return adminMapper.veritypasswd(name,password)>0?true:false;
    }
}
