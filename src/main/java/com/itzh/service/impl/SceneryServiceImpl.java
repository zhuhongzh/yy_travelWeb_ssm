package com.itzh.service.impl;

import com.itzh.dao.ISceneryDao;
import com.itzh.domain.Scenery;
import com.itzh.service.ISceneryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("sceneryService")
public class SceneryServiceImpl implements ISceneryService {
    @Resource(name = "sceneryDao")
    private ISceneryDao sceneryDao;
    @Override
    public List<Scenery> findSceneryByRand() {
        return sceneryDao.findSceneryByRand();
    }

    @Override
    public List<Scenery> findSceneryByTagId(int id) {
        return sceneryDao.findSceneryByTagId(id);
    }

    @Override
    public Scenery findSceneryById(int id) {
        return sceneryDao.findSceneryById(id);
    }

    @Override
    public String findSceneryNameById(int id) {
        return sceneryDao.findSceneryNameById(id);
    }

    @Override
    public List<Scenery> findCollectedSceneryById(int id) {
        return sceneryDao.findCollectedSceneryById(id);
    }

    @Override
    public List<Scenery> findAllScenery() {
        return sceneryDao.findAllScenery();
    }

    @Override
    public List<Scenery> findScenery(String content) {
        return sceneryDao.findScenery(content);
    }
}
