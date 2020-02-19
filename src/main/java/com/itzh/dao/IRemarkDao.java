package com.itzh.dao;

import com.itzh.domain.Note;
import com.itzh.domain.Remark;
import com.itzh.domain.Scenery;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Repository("remarkDao")
public interface IRemarkDao {
    /**
     * 随机查找10个景区
     * @return
     */
    @Select("select * from remark order by rand() limit 10")
    @Results(id = "remarkMap",value = {
            @Result(id = true,property = "remarkId",column = "remark_id"),
            @Result(property="remarkTitle",column="remark_title"),
            @Result(property="remarkContent",column="remark_content"),
            @Result(property="remarkTime" ,column="remark_time"),
            @Result(property="sceneryId",column="scenery_id"),
            @Result(property="userId",column="user_id"),
            @Result(property = "user",column = "user_id",
                    one = @One(select = "com.itzh.dao.IUserDao.findUserById",
                            fetchType = FetchType.EAGER)),
            @Result(property = "sceneryName",column = "scenery_id",
                    one = @One(select = "com.itzh.dao.ISceneryDao.findSceneryNameById",
                            fetchType = FetchType.EAGER)
            ),
            @Result(property = "remarkImgList",column = "remark_id",
                    many = @Many(select = "com.itzh.dao.IRemarkImgDao.findRemarkImgById",
                            fetchType = FetchType.LAZY)
            )
    })
    List<Remark> findRemarkByRand();



    /**
     * 根据remark_id查找景区
     * @param id
     * @return
     */
    @Select("select * from remark where remark_id=#{id}")
    @ResultMap(value = "remarkMap")
    Remark findRemarkById(int id);

    //添加新note
    @Insert("insert into remark (remark_title,remark_content,remark_time,scenery_id,user_id) values(" +
            "#{remarkTitle},#{remarkContent},#{remarkTime},#{sceneryId},#{userId})")
    @Options(useGeneratedKeys = true,keyProperty = "remarkId",keyColumn = "remark_id")
    void saveRemark(Remark remark);
}
