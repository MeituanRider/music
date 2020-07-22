package com.aftvc.top.service;

import com.aftvc.top.domain.Consumer;
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
public interface ConsumerService extends IService<Consumer> {
    boolean addUser(Consumer consumer);

    boolean verifyPassword(String username,String password);

    List<Consumer> loginStatus(String username);

    List<Consumer> allUser();

    List<Consumer> UserOfId(Integer id);

    boolean deleteUserById(Integer id);

    int updateUserMsg(Consumer consumer);

    boolean updateUserAvator(Consumer consumer);

    boolean isOnly(String username);
}
