package com.moon.note.mapper;

import com.moon.note.entity.Note;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JinHui
 * @date 2021/8/14
 */

@Repository
public interface NoteDao {

    @Value("${}")

    @Select("select id,AES_DECRYPT(title,'lsk-art') as title, time,authorId, AES_DECRYPT(content, 'lsk-art') as content from notes where authorId = #{authorId} and deleted = false order by time desc")
    List<Note> selectNotesByAuthorId(String authorId);

    @Insert("insert into notes(title,content,time,authorId) values(AES_ENCRYPT((#{title}),'lsk-art'),AES_ENCRYPT((#{content}),'lsk-art'),NOW(),#{authorId})")
    @ResultType(Boolean.class)
    boolean insertNote(Note note);

    @ResultType(Boolean.class)
    @Delete("update notes set deleted = true where id = #{noteId}")
    boolean deleteNoteById(String noteId);

    @ResultType(Boolean.class)
    @Update("update notes set title = #{title} , content = #{content} , time = NOW() where id = #{id}")
    boolean updateNote(Note note);

}
