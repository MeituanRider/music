package com.aftvc.top.service.impl;

import com.aftvc.top.domain.Rank;
import com.aftvc.top.dao.RankMapper;
import com.aftvc.top.service.RankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
@Service
public class RankServiceImpl extends ServiceImpl<RankMapper, Rank> implements RankService {
    @Autowired
    private RankMapper rankMapper;
    @Override
    public int rankOfSongListId(Long songListId) {
        return rankMapper.ScoreSum(songListId)/rankMapper.ScoreCount(songListId);
    }

    @Override
    public int addRank(Rank rank) {
        return rankMapper.insert(rank);
    }
}
