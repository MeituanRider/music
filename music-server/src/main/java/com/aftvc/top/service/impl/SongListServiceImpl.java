package com.aftvc.top.service.impl;

import com.aftvc.top.domain.SongList;
import com.aftvc.top.dao.SongListMapper;
import com.aftvc.top.service.SongListService;
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
public class SongListServiceImpl extends ServiceImpl<SongListMapper, SongList> implements SongListService {
    @Autowired
    private SongListMapper songListMapper;
    @Override
    public boolean addSongList(SongList songList) {
        return songListMapper.insert(songList)>0?true:false;
    }

    @Override
    public List<SongList> allSongList() {
        return songListMapper.selectList(null);
    }

    @Override
    public List<SongList> songListOfTitle(String title) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("title",title);
        return songListMapper.selectList(wrapper);
    }

    @Override
    public List<SongList> songListOfStyle(String style) {
        QueryWrapper<SongList> wrapper = new QueryWrapper<>();
        wrapper.like("style",style);
        return songListMapper.selectList(wrapper);
    }

    @Override
    public int updateSongList(SongList songList) {
        return songListMapper.updateById(songList);
    }

    @Override
    public boolean deleteSongList(int id) {
        return songListMapper.deleteById(id)>0?true:false;
    }

    @Override
    public boolean updateSongListImg(SongList songList) {
        UpdateWrapper<SongList> wrapper = new UpdateWrapper<>();
        wrapper.set("pic",songList.getPic());
        wrapper.eq("id",songList.getId());
        return songListMapper.update(songList,wrapper)>0?true:false;
    }
}
