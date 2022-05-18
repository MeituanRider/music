package com.aftvc.top.service;

import com.aftvc.top.domain.Ranks;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yan
 * @since 2020-07-09
 */
public interface RankService extends IService<Ranks> {
    int rankOfSongListId(Long songListId);

    int addRank(Ranks ranks);

    Ranks selectRankByIds(String songListId, String consumerId);
}
