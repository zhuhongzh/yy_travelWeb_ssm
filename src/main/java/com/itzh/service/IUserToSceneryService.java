package com.itzh.service;

import com.itzh.domain.UserToScenery;
import org.apache.ibatis.annotations.*;

public interface IUserToSceneryService {

    UserToScenery saveUserToScenery(int userId, int sceneryId);


    UserToScenery findUserToScenery(int userId, int sceneryId);

    void deleteUserToScenery(int userId,int sceneryId);
}
