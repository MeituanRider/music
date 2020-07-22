package com.aftvc.top.service;

import com.aftvc.top.domain.ResponseBean;
import com.aftvc.top.domain.SongList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
public interface SongListService extends IService<SongList> {

    boolean addSongList(SongList songList);

    List<SongList> allSongList();

    List<SongList> songListOfTitle(String title);

    List<SongList> songListOfStyle(String style);

    int updateSongList(SongList songList);

    boolean deleteSongList(int id);

    boolean updateSongListImg(SongList songList);
}
