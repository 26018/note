package com.moon.note.controller;

import com.moon.note.entity.Note;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.UserToken;
import com.moon.note.service.NoteService;
import com.moon.note.utils.StringUtil;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author MoonLight
 */

@RestController()
public class NoteController {

    @Resource
    NoteService noteService;

    @Resource
    HashMap<String, UserToken> userTokensMap;

    @GetMapping("/notes")
    public Result<String> notes(HttpServletRequest request) {
        return noteService.notes(userTokensMap.get(request.getHeader("token")).getUser().getId());
    }

    @PostMapping("/notes")
    public Result<String> insertNote(HttpServletRequest request) {
        final String noteString = request.getParameter("noteString");
        if (!StringUtil.valid(noteString)) {
            return new Result<>(Response.PARAMETER_IS_NULL);
        }
        return noteService.insertNote(new Note(noteString, userTokensMap.get(request.getHeader("token")).getUser().getId()));
    }

    @DeleteMapping("/notes/{noteId}")
    public Result<String> deleteNote(@PathVariable("noteId") String noteId) {
        return noteService.deleteNoteById(noteId);
    }

    @PutMapping("/notes/{noteId}")
    public Result<String> noteUpdate(HttpServletRequest request, @PathVariable("noteId") String noteId) {
        return noteService.noteUpdate(request.getParameter("noteString"), noteId);
    }

}
