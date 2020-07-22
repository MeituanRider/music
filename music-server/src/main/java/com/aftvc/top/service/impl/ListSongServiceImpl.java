package com.aftvc.top.service.impl;

import com.aftvc.top.domain.ListSong;
import com.aftvc.top.dao.ListSongMapper;
import com.aftvc.top.service.ListSongService;
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
public class ListSongServiceImpl extends ServiceImpl<ListSongMapper, ListSong> implements ListSongService {
    @Autowired
    private ListSongMapper listSongMapper;

    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.selectList(null);
    }

    @Override
    public List<ListSong> listSongOfSongId(Integer songListId) {
        QueryWrapper<ListSong> wrapper = new QueryWrapper<>();
        wrapper.eq("song_list_id",songListId);
        return listSongMapper.selectList(wrapper);
    }

    @Override
    public int addListSong(ListSong listSong) {
        return listSongMapper.insert(listSong);
    }

    @Override
    public boolean updateListSongMsg(ListSong listSong) {
        return listSongMapper.updateById(listSong)>0?true:false;
    }

    @Override
    public int deleteListSong(int songId) {
        UpdateWrapper<ListSong> wrapper = new UpdateWrapper<>();
        wrapper.eq("song_id",songId);
        return listSongMapper.delete(wrapper);
    }
}
