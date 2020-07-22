package com.aftvc.top.service.impl;

import com.aftvc.top.domain.Consumer;
import com.aftvc.top.domain.Singer;
import com.aftvc.top.dao.SingerMapper;
import com.aftvc.top.service.SingerService;
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
public class SingerServiceImpl extends ServiceImpl<SingerMapper, Singer> implements SingerService {
    @Autowired
    private SingerMapper singerMapper;
    @Override
    public List<Singer> allSinger() {
        return singerMapper.selectList(null);
    }

    @Override
    public int addSinger(Singer singer) {
        return singerMapper.insert(singer);
    }

    @Override
    public List<Singer> singerOfName(String name) {
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();
        wrapper.like("name",name);
        return singerMapper.selectList(wrapper);
    }

    @Override
    public List<Singer> singerOfSex(String sex) {
        QueryWrapper<Singer> wrapper = new QueryWrapper<>();
        wrapper.eq("sex",sex);
        return singerMapper.selectList(wrapper);
    }

    @Override
    public int deleteSinger(Integer id) {
        return singerMapper.deleteById(id);
    }

    @Override
    public int updateSinger(Singer singer) {
        UpdateWrapper<Singer> wrapper = new UpdateWrapper<>();
        return singerMapper.updateById(singer);
    }

    @Override
    public boolean updateSingerPic(Singer singer) {
        /*UpdateWrapper<Singer> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",singer.getId());*/
        return singerMapper.updateSingerPic(singer);
    }
}
