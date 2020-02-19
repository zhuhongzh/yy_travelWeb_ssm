package com.itzh.dao;

import com.itzh.domain.Note;
import com.itzh.domain.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("noteDao")
public interface INoteDao {
    /**
     * 根据user_id查找note
     * @param id
     * @return
     */
    @Select("select * from note where user_id=#{id}")
    @Results(id = "noteMap",value ={
            @Result(id = true,property = "noteId",column = "note_id"),
            @Result(property = "noteTitle",column = "note_title"),
            @Result(property = "noteContent",column = "note_content"),
            @Result(property = "noteTime",column = "note_time"),
            @Result(property = "sceneryId",column = "scenery_id"),
            @Result(property = "userId",column = "user_id"),
            @Result(property = "state",column = "state"),
            @Result(property = "sceneryName",column = "scenery_id",
            one = @One(select = "com.itzh.dao.ISceneryDao.findSceneryNameById",fetchType = FetchType.EAGER))

    })
    List<Note> findNoteByUserId(int id);

    /**
     * 获取note根据偏移量
     * @param num
     * @return
     */
    @Select("select * from note limit 10 offset #{num}")
    @ResultMap(value = "noteMap")
    List<Note> findNoteLimit(int num);

    @Select("select * from note where user_id=#{id} and state=#{state}")
    @ResultMap(value = "noteMap")
    List<Note> findNoteByState(@Param("id") int id,@Param("state") int state);

    @Select("select * from note where note_id=#{id}")
    @ResultMap(value = "noteMap")
    Note findNoteById(int id);

    //更新note信息
    @Update("update note set note_title=#{noteTitle},note_content=#{noteContent},note_time=#{noteTime},scenery_id="+
            "#{sceneryId},user_id=#{userId},state=#{state} where note_id=#{noteId}")
    int updateNote(Note note);

    //添加新note
    @Insert("insert into note (note_title,note_content,note_time,scenery_id,user_id,state) values(" +
            "#{noteTitle},#{noteContent},#{noteTime},#{sceneryId},#{userId},#{state})")
    @Options(useGeneratedKeys = true,keyProperty = "noteId",keyColumn = "note_id")
    void saveNote(Note note);

    @Delete("delete from note where note_id=#{id}")
    int deleteNote(int id);
}
