package com.itzh.dao;

import com.itzh.domain.Note;
import com.itzh.domain.UserToScenery;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userToSceneryDao")
public interface IUserToSceneryDao {

    //添加新note
    @Insert("insert into usertoscenery (user_id,scenery_id) values(#{userId},#{sceneryId})")
    @Options(useGeneratedKeys = true,keyProperty = "noteId",keyColumn = "note_id")
    void saveUserToScenery(@Param("userId")int userId,@Param("sceneryId") int sceneryId);

    @Select("select * from usertoscenery where user_id = #{userId} and scenery_id=#{sceneryId}")
    @Results(id = "userToSceneryMap",value ={
            @Result(id = true,property = "id",column = "user_scenery_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "sceneryId",column = "scenery_id"),
    })
    UserToScenery findUserToScenery(@Param("userId")int userId,@Param("sceneryId") int sceneryId);

    @Delete("delete from usertoscenery where user_id = #{userId} and scenery_id=#{sceneryId}")
    void deleteUserToScenery(@Param("userId")int userId,@Param("sceneryId") int sceneryId);
}
