package com.itzh.service.impl;

import com.itzh.dao.INoteDao;
import com.itzh.domain.Note;
import com.itzh.service.INoteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service("noteService")
public class NoteServiceImpl implements INoteService {

    @Resource(name = "noteDao")
    private INoteDao noteDao;

    @Override
    public List<Note> findNoteByUserId(int id) {
        return noteDao.findNoteByUserId(id);
    }

    @Override
    public List<Note> findNoteByState(int id, int state) {
        return noteDao.findNoteByState(id,state);
    }

    @Override
    public List<Note> findNoteLimit(int num) {
        return noteDao.findNoteLimit(num);
    }

    @Override
    public Note updateNote(Note note) {
        noteDao.updateNote(note);
        return noteDao.findNoteById(note.getNoteId());
    }

    @Override
    public Note saveNote(Note note) {
        noteDao.saveNote(note);
        int id = note.getNoteId();
        return noteDao.findNoteById(id);
    }

    @Override
    public int deleteNote(int id) {
        return noteDao.deleteNote(id);
    }
}
