package com.itzh.dao;

import com.itzh.domain.Scenery;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("sceneryDao")
public interface ISceneryDao {
    /**
     * 随机查找10个景区
     * @return
     */
    @Select("select * from scenery order by rand() limit 10")
    @Results(id = "sceneryMap",value = {
            @Result(id = true,property = "sceneryId",column = "scenery_id"),
            @Result(property = "sceneryName",column = "scenery_name"),
            @Result(property = "sceneryDescription",column = "scenery_description"),
            @Result(property = "sceneryPrice",column = "scenery_price"),
            @Result(property = "sceneryLocation",column = "scenery_location"),
            @Result(property = "sceneryTagId",column = "scenery_tag_id"),
            @Result(property = "sceneryTag",column = "scenery_tag_id",
                    one = @One(select = "com.itzh.dao.ISceneryTagDao.findTagNameById",
                            fetchType = FetchType.EAGER)),
            @Result(property = "sceneryImgs",column = "scenery_id",
                    many = @Many(select = "com.itzh.dao.ISceneryImgDao.findSceneryImgById",
                            fetchType = FetchType.LAZY)),
            @Result(property = "goodsList",column = "scenery_id",
                    many = @Many(select = "com.itzh.dao.IGoodsDao.findGoods",
                            fetchType = FetchType.LAZY)),
    })
    List<Scenery> findSceneryByRand();

    /**
     * 根据tag_id查找景区
     * @param id
     * @return
     */
    @Select("select * from scenery where scenery_tag_id=#{id}")
    @ResultMap(value = "sceneryMap")
    List<Scenery> findSceneryByTagId(int id);

    /**
     * 查找所有景区
     * @return
     */
    @Select("select * from scenery")
    @ResultMap(value = "sceneryMap")
    List<Scenery> findAllScenery();

    /**
     * 根据信息查找景区
     * @return
     */
    @Select("select * from scenery where scenery_name like '%${content}%' " +
            "or scenery_description like '%${content}%' or scenery_location like '%${content}%'")
    @ResultMap(value = "sceneryMap")
    List<Scenery> findScenery(@Param("content")String content);

    /**
     * 根据scenery_id查找景区
     * @param id
     * @return
     */
    @Select("select * from scenery where scenery_id=#{id}")
    @ResultMap(value = "sceneryMap")
    Scenery findSceneryById(int id);

    /**
     * 根据scenery_id查找景区的名字
     * @param id
     * @return
     */
    @Select("select scenery_name from scenery where scenery_id=#{id}")
    String findSceneryNameById(int id);

    /**
     * 根据scenery_id查找被指定user_id收藏的景点
     * @param id
     * @return
     */
    @Select("select * from scenery where scenery_id in (select scenery_id from usertoscenery where user_id = #{id})")
    @Results(value = {
            @Result(id = true,property = "sceneryId",column = "scenery_id"),
            @Result(property = "sceneryName",column = "scenery_name"),
            @Result(property = "sceneryDescription",column = "scenery_description"),
            @Result(property = "sceneryPrice",column = "scenery_price"),
            @Result(property = "sceneryLocation",column = "scenery_location"),
            @Result(property = "sceneryTagId",column = "scenery_tag_id"),
            @Result(property = "sceneryTag",column = "scenery_tag_id",
                    one = @One(select = "com.itzh.dao.ISceneryTagDao.findTagNameById",
                            fetchType = FetchType.EAGER)),
            @Result(property = "sceneryImgs",column = "scenery_id",
                    many = @Many(select = "com.itzh.dao.ISceneryImgDao.findSceneryImgById",
                            fetchType = FetchType.LAZY)),
    })
    List<Scenery> findCollectedSceneryById(int id);
}
