package com.aftvc.top.dao;

import com.aftvc.top.domain.Consumer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public interface ConsumerMapper extends BaseMapper<Consumer> {

    int verifyPassword(String username,String password);
    int updateUserAvator(Consumer consumer);
    int isOnly(String username);
}
