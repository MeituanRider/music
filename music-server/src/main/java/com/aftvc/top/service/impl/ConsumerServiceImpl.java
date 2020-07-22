package com.aftvc.top.service.impl;

import com.aftvc.top.domain.Consumer;
import com.aftvc.top.dao.ConsumerMapper;
import com.aftvc.top.service.ConsumerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@Service
public class ConsumerServiceImpl extends ServiceImpl<ConsumerMapper, Consumer> implements ConsumerService {
    @Autowired
    private ConsumerMapper consumerMapper;
    @Override
    public boolean addUser(Consumer consumer) {
        return consumerMapper.insert(consumer)>0?true:false;
    }

    @Override
    public boolean verifyPassword(String username, String password) {
        return consumerMapper.verifyPassword(username,password)>0?true:false;
    }

    @Override
    public List<Consumer> loginStatus(String username) {
        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        return consumerMapper.selectList(wrapper);
    }

    @Override
    public List<Consumer> allUser() {
        return consumerMapper.selectList(null);
    }

    @Override
    public List<Consumer> UserOfId(Integer id) {
        QueryWrapper<Consumer> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return consumerMapper.selectList(wrapper);
    }

    @Override
    public boolean deleteUserById(Integer id) {
        return consumerMapper.deleteById(id)>0?true:false;
    }

    @Override
    public int updateUserMsg(Consumer consumer) {
        UpdateWrapper<Consumer> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",consumer.getId());
        int result = consumerMapper.update(consumer, wrapper);
        return result;
    }

    @Override
    public boolean updateUserAvator(Consumer consumer) {
        /*UpdateWrapper<Consumer> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",consumer.getId());*/
        boolean update = consumerMapper.updateUserAvator(consumer)>0?true:false;
        return update;
    }

    @Override
    public boolean isOnly(String username) {
        return consumerMapper.isOnly(username)>0?true:false;
    }


}
