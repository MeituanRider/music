package com.aftvc.top.service.impl;

import com.aftvc.top.domain.Collect;
import com.aftvc.top.dao.CollectMapper;
import com.aftvc.top.service.CollectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public class CollectServiceImpl extends ServiceImpl<CollectMapper, Collect> implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Override
    public List<Collect> allCollect() {
        return collectMapper.selectList(null);
    }

    @Override
    public List<Collect> collectionOfUser(int userId) {
        QueryWrapper<Collect> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",userId);
        return collectMapper.selectList(wrapper);
    }

    @Override
    public boolean existSongId(int userId, int songId) {
        return collectMapper.existSongId(userId,songId)>0?true:false;
    }

    @Override
    public boolean addCollection(Collect collect) {
        return collectMapper.insert(collect)>0?true:false;
    }

    @Override
    public boolean updateCollectMsg(Collect collect) {
        return collectMapper.updateById(collect)>0?true:false;
    }

    @Override
    public boolean deleteCollect(int userId, int songId) {
        return collectMapper.deleteCollect(userId,songId)>0?true:false;
    }
}
