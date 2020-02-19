package com.itzh.dao;

import com.itzh.domain.SceneryImg;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("sceneryImgDao")
public interface ISceneryImgDao {

    @Select("select * from sceneryimgs where scenery_id=#{id}")
    @Results(id = "imgMap",value = {
            @Result(id = true,property = "imgId",column = "img_id"),
            @Result(property = "imgUrl",column = "img_url"),
            @Result(property = "sceneryId",column = "scenery_id")
    })
    List<SceneryImg> findSceneryImgById(int id);
}
