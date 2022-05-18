package com.aftvc.top.dao;

import com.aftvc.top.domain.Collect;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface CollectMapper extends BaseMapper<Collect> {
    int existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    int deleteCollect(@Param("userId") Integer userId, @Param("songId") Integer songId);

    Collect selectCollection(String userId,String songId);
}
