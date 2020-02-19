package com.itzh.service.impl;

import com.itzh.dao.ISceneryTagDao;
import com.itzh.domain.SceneryTag;
import com.itzh.service.ISceneryTagService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("sceneryTagService")
public class SceneryTagService implements ISceneryTagService {
    @Resource(name = "sceneryTagDao")
    private ISceneryTagDao sceneryTagDao;

    @Override
    public List<SceneryTag> findAllSceneryTags() {
        return sceneryTagDao.findAllSceneryTags();
    }
}
