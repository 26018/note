package com.moon.note.service;

import com.alibaba.fastjson.JSON;
import com.moon.note.entity.Note;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.mapper.NoteDao;
import com.moon.note.utils.StringUtil;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.sql.Struct;

/**
 * @author JinHui
 * @date 2022/3/12
 */

@Service
public class NoteService {

    @Resource
    NoteDao noteDao;

    public Result<String> notes(long authorId) {
        return new Result<>(Response.SUCCESS, JSON.toJSONString(noteDao.selectNotesByAuthorId(String.valueOf(authorId))));
    }

    public Result<String> insertNote(Note note) {
        return returnCountCheck(noteDao.insertNote(note));
    }

    public Result<String> deleteNoteById(String id) {
        return returnCountCheck(noteDao.deleteNoteById(id));
    }

    public Result<String> noteUpdate(String noteString, String id) {
        if (!StringUtil.valid(noteString, id)) {
            return new Result<>(Response.PARAMETER_IS_NULL);
        }
        return returnCountCheck(noteDao.updateNote(new Note(Long.parseLong(id), noteString)));
    }

    /**
     * @param b
     * @return 返回结果
     */
    private Result<String> returnCountCheck(Boolean b) {
        return b ? new Result<>(Response.SUCCESS) : new Result<>(Response.FAIL);
    }

}
