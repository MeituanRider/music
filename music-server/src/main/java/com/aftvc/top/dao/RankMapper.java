package com.aftvc.top.dao;

import com.aftvc.top.domain.Rank;
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
public interface RankMapper extends BaseMapper<Rank> {
    int ScoreSum(Long songListId);
    int ScoreCount(Long songListId);
}
