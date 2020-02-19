package com.itzh.service.impl;

import com.itzh.dao.IUserDao;
import com.itzh.domain.Scenery;
import com.itzh.domain.User;
import com.itzh.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource(name = "userDao")
    private IUserDao userDao;

    @Override
    public List<User> findAllUsers() {
        System.out.println("业务层执行");
        return userDao.findAllUsers();
    }

    @Override
    public User findUserByPhone(String phone) {
        return userDao.findUserByPhone(phone);
    }

    public User findUserById(int id){
        return userDao.findUserById(id);
    }

    @Override
    public User updateUser(User user) {
        userDao.updateUser(user);
        return userDao.findUserByPhone(user.getPhone());
    }

    @Override
    public User findCollectedSceneryById(int id) {
        return userDao.findUserCollcetSceneryById(id);
    }

    @Override
    public User saveUser(User user) {
        System.out.println("业务层执行了");
        User u = userDao.findUserByPhone(user.getPhone());
        User s = userDao.findUserByEamil(user.getEmail());
        if(u == null && s == null){
            userDao.saveUser(user);
            return userDao.findUserByPhone(user.getPhone());
        }else {
            return null;
        }
    }
}
