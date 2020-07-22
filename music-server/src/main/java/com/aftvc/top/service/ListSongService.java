package com.aftvc.top.service;

import com.aftvc.top.domain.ListSong;
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
public interface ListSongService extends IService<ListSong> {
    List<ListSong> allListSong();

    List<ListSong> listSongOfSongId(Integer songListId);

    int addListSong(ListSong listSong);

    boolean updateListSongMsg(ListSong listSong);

    int deleteListSong(int songId);
}
