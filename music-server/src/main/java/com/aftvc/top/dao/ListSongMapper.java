package com.aftvc.top.dao;

import com.aftvc.top.domain.ListSong;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@Repository
public interface ListSongMapper extends BaseMapper<ListSong> {
   /*List<ListSong> listSongOfSongId(Integer songListId);*/
}
