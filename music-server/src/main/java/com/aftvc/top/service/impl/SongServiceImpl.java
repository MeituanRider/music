package com.aftvc.top.service.impl;

import com.aftvc.top.domain.Song;
import com.aftvc.top.dao.SongMapper;
import com.aftvc.top.service.SongService;
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
public class SongServiceImpl extends ServiceImpl<SongMapper, Song> implements SongService {
    @Autowired
    private SongMapper songMapper;
    @Override
    public List<Song> allSongs() {
        return songMapper.selectList(null);
    }

    @Override
    public List<Song> songOfId(Integer id) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> songOfSingerid(Integer singerId) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.eq("singer_id",singerId);
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> songOfSingerName(String name) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.like("name",name);
        return songMapper.selectList(wrapper);
    }

    @Override
    public List<Song> songOfName(String name) {
        QueryWrapper<Song> wrapper = new QueryWrapper<>();
        wrapper.like("name",name);
        return songMapper.selectList(wrapper);
    }

    @Override
    public int deleteSongById(String id) {
        return songMapper.deleteById(id);
    }

    @Override
    public boolean addSong(Song song) {
        return songMapper.insert(song)>0?true:false;
    }

    @Override
    public boolean updateSongMsg(Song song) {
        UpdateWrapper<Song> wrapper = new UpdateWrapper<>();
        wrapper.eq("id",song.getId());
        return songMapper.update(song,wrapper)>0?true:false;
    }

    @Override
    public boolean updateSongPic(Song song) {
        return songMapper.updateSongPic(song)>0?true:false;
    }

    @Override
    public boolean updateSongUrl(Song song) {
        UpdateWrapper<Song> wrapper = new UpdateWrapper<>();
        wrapper.set("url",song.getUrl());
        wrapper.eq("id",song.getId());
        return songMapper.update(song,wrapper)>0?true:false;
    }
}
