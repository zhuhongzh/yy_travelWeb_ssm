package com.itzh.service;

import com.itzh.domain.Note;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface INoteService {
    /**
     * 根据user_id查找note
     * @param id
     * @return
     */

    List<Note> findNoteByUserId(int id);

    List<Note> findNoteByState(int id,int state);

    /**
     * 获取note根据偏移量
     * @param num
     * @return
     */
    List<Note> findNoteLimit(int num);

    //更新NOTE信息
    Note updateNote(Note note);

    Note saveNote(Note note);

    int deleteNote(int id);
}
