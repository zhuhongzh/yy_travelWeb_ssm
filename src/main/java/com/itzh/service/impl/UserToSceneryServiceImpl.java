package com.itzh.service.impl;

import com.itzh.dao.IUserToSceneryDao;
import com.itzh.domain.UserToScenery;
import com.itzh.service.IUserToSceneryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userToSceneryService")
public class UserToSceneryServiceImpl implements IUserToSceneryService {
    @Resource(name = "userToSceneryDao")
    private IUserToSceneryDao userToSceneryDao;


    @Override
    public UserToScenery saveUserToScenery(int userId,int sceneryId) {
        UserToScenery userToScenery = userToSceneryDao.findUserToScenery(userId,sceneryId);
        if(userToScenery != null){
            userToSceneryDao.deleteUserToScenery(userId,sceneryId);
        }else {
            userToSceneryDao.saveUserToScenery(userId,sceneryId);
        }
        return userToSceneryDao.findUserToScenery(userId,sceneryId);
    }

    @Override
    public UserToScenery findUserToScenery(int userId, int sceneryId) {
        return userToSceneryDao.findUserToScenery(userId,sceneryId);
    }

    @Override
    public void deleteUserToScenery(int userId, int sceneryId) {
        userToSceneryDao.deleteUserToScenery(userId,sceneryId);
    }
}
