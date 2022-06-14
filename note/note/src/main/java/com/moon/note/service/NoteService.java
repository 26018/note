package com.moon.note.service;

import com.moon.note.annotation.SystemServiceLog;
import com.moon.note.entity.Note;
import com.moon.note.mapper.NoteDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author JinHui
 * @date 2022/3/12
 */

@Service

public class NoteService {

    @Resource
    NoteDao noteDao;
    @SystemServiceLog
    public List<Note> allNotes(String authorId) {
        return noteDao.getNotesByAuthorId(authorId);
    }

    public int noteCount(String authorId) {
        return noteDao.noteCount(authorId);
    }

    public void insert(Note note) {
        noteDao.insertNote(note);
    }

    public void deleteById(String id) {
        noteDao.deleteNoteById(id);
    }

    public void update(String noteString, String id) {
        noteDao.updateNote(new Note(Long.parseLong(id), noteString));
    }

}
