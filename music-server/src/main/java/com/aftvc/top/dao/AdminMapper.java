package com.aftvc.top.dao;

import com.aftvc.top.domain.Admins;
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
public interface AdminMapper extends BaseMapper<Admins> {
    int veritypasswd(String name,String password);
}
