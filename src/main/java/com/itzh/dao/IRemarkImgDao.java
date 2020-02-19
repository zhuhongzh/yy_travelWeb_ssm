package com.itzh.dao;

import com.itzh.domain.RemarkImg;
import com.itzh.domain.SceneryImg;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("remarkImgDao")
public interface IRemarkImgDao {
    @Select("select * from remarkimgs where remark_id=#{id}")
    @Results(value = {
            @Result(id = true,property = "imgId",column = "img_id"),
            @Result(property = "imgUrl",column = "img_url"),
            @Result(property = "remarkId",column = "remark_id")
    })
    List<RemarkImg> findRemarkImgById(int id);

    @Insert("insert into remarkimgs(img_url,remark_id) values(#{imgUrl},#{remarkId})")
    @Options(useGeneratedKeys = true,keyProperty = "imgId",keyColumn = "img_id")
    void saveRemarkImg(@Param("imgUrl")String imgUrl,@Param("remarkId") int remarkId);
}
