package com.aftvc.top.dao;

import com.aftvc.top.domain.Song;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@Repository
public interface SongMapper extends BaseMapper<Song> {
    int updateSongPic(Song song);
}
