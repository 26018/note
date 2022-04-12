package com.moon.note.controller;

import com.alibaba.fastjson.JSON;
import com.moon.note.entity.Note;
import com.moon.note.entity.Response;
import com.moon.note.entity.Result;
import com.moon.note.entity.User;
import com.moon.note.service.NoteService;
import com.moon.note.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author MoonLight
 */

@RestController()
public class NoteController {

    @Resource
    NoteService noteService;
    @Resource
    UserService userService;

    @GetMapping("/notes")
    public Result<String> notes(@NotBlank(message = "token不能为空")
                                @RequestHeader String token) throws Exception {
        User user = userService.getByToken(token);
        List<Note> notes = noteService.allNotes(String.valueOf(user.getId()));
        return new Result<>(Response.SUCCESS, JSON.toJSONString(notes));
    }

    @PostMapping("/notes")
    public Result<String> insert(
            @NotBlank(message = "token不能为空") @RequestHeader String token,
            @NotBlank(message = "note为不能空") @RequestParam String noteString) throws Exception {
        User user = userService.getByToken(token);
        noteService.insert(new Note(noteString, user.getId()));
        return new Result<>(Response.SUCCESS);
    }

    @DeleteMapping("/notes/{noteId}")
    public Result<String> delete(@NotBlank(message = "noteId不能为空")
                                 @PathVariable("noteId") String noteId) {
        noteService.deleteById(noteId);
        return new Result<>(Response.SUCCESS);
    }

    @PutMapping("/notes/{noteId}")
    public Result<String> update(@NotBlank(message = "note不能为空")
                                 @RequestParam String noteString,
                                 @NotBlank(message = "noteId不能为空")
                                 @PathVariable("noteId") String noteId) {
        noteService.update(noteString, noteId);
        return new Result<>(Response.SUCCESS);
    }
}
