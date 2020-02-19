package com.itzh.service;

import com.itzh.domain.Scenery;
import com.itzh.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserService {

    List<User> findAllUsers();

    User findUserByPhone(String phone);

    User saveUser(User user);

    User findUserById(int id);

    User updateUser(User user);

    /**
     * 根据scenery_id查找被指定user_id收藏的景点
     * @param id
     * @return
     */
    User findCollectedSceneryById(int id);
}
