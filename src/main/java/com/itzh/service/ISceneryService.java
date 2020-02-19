package com.itzh.service;

import com.itzh.domain.Scenery;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ISceneryService {
    /**
     * 随机查找10个景区
     * @return
     */
    List<Scenery> findSceneryByRand();

    /**
     * 根据tag_id查找景区
     * @param id
     * @return
     */
    List<Scenery> findSceneryByTagId(int id);

    /**
     * 根据scenery_id查找景区
     * @param id
     * @return
     */
    Scenery findSceneryById(int id);

    /**
     * 根据scenery_id查找景区的名字
     * @param id
     * @return
     */
    String findSceneryNameById(int id);

    /**
     * 根据scenery_id查找被指定user_id收藏的景点
     * @param id
     * @return
     */
    List<Scenery> findCollectedSceneryById(int id);

    /**
     * 查找所有景区
     * @return
     */
    List<Scenery> findAllScenery();

    /**
     * 根据信息查找景区
     * @return
     */
    List<Scenery> findScenery(String content);
}
