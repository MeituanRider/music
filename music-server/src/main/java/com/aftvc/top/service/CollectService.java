package com.aftvc.top.service;

import com.aftvc.top.domain.Collect;
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
public interface CollectService extends IService<Collect> {

    List<Collect> allCollect();

    List<Collect> collectionOfUser(int userId);

    boolean existSongId(int userId, int songId);

    boolean addCollection(Collect collect);

    boolean updateCollectMsg(Collect collect);

    boolean deleteCollect(int userId, int songId);

}
