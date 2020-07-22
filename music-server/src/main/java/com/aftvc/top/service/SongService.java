package com.aftvc.top.service;

import com.aftvc.top.domain.Song;
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
public interface SongService extends IService<Song> {
    List<Song> allSongs();
    List<Song> songOfId(Integer id);
    List<Song> songOfSingerid(Integer singerId);
    List<Song> songOfSingerName(String name);

    List<Song> songOfName(String name);

    int deleteSongById(String id);

    boolean addSong(Song song);

    boolean updateSongMsg(Song song);

    boolean updateSongPic(Song song);

    boolean updateSongUrl(Song song);
}
