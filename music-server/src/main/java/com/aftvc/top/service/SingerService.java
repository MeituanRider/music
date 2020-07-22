package com.aftvc.top.service;

import com.aftvc.top.domain.Singer;
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
public interface SingerService extends IService<Singer> {
    List<Singer> allSinger();
    int addSinger(Singer singer);
    List<Singer> singerOfName(String name);
    List<Singer> singerOfSex(String sex);
    int deleteSinger(Integer id);
    int updateSinger(Singer singer);
    boolean updateSingerPic(Singer singer);
}
