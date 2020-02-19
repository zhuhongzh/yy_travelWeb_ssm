package com.itzh.dao;

import com.itzh.domain.Scenery;
import com.itzh.domain.SceneryTag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("sceneryTagDao")
public interface ISceneryTagDao {
    /**
     * 根据TagId查询该Tag_name
     */
    @Select("select * from scenerytag where tag_id=#{scenery_tag_id}")
    @Results(id = "tagMap",value = {
            @Result(id = true,property = "tagId",column = "tag_id"),
            @Result(property = "tagName",column = "tag_name")
    })
    SceneryTag findTagNameById(int sceneryTagId);


//    /**
//     * 查找指定Tag_name的景区
//     * @param tagName
//     * @return
//     */
//    @Select("select * from scenerytag where tag_name=#{tag_name}")
//    @Results(id = "tagMap",value = {
//            @Result(id = true,property = "tagId",column = "tag_id"),
//            @Result(property = "tagName",column = "tag_name"),
//            @Result(property = "sceneryList",column = "tag_id",
//                    many = @Many(select = "com.itzh.dao.ISceneryDao.findSceneryByTagId",
//                            fetchType = FetchType.LAZY))
//    })
//    List<Scenery> findSceneryByTag(String tagName);

    @Select("select * from scenerytag")
    @ResultMap(value = "tagMap")
    List<SceneryTag> findAllSceneryTags();
}
