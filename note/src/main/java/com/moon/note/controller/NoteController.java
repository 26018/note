package com.moon.note.controller;

import com.moon.note.entity.*;
import com.moon.note.service.NoteService;
import com.moon.note.service.UserService;
import com.moon.note.utils.StringUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;

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
    public Result<String> notes(
            @NotBlank(message = "token不能为空") @RequestHeader String token) {
        return noteService.notes(userService.getByToken(token).getId());
    }

    @PostMapping("/notes")
    public Result<String> insertNote(
            @NotBlank(message = "token不能为空") @RequestHeader String token,
            @NotBlank(message = "note为不能空") @RequestParam String noteString) {
        return noteService.insertNote(new Note(noteString, userService.getByToken(token).getId()));
    }

    @DeleteMapping("/notes/{noteId}")
    public Result<String> deleteNote(@NotBlank(message = "noteId不能为空") @PathVariable("noteId") String noteId) {
        return noteService.deleteNoteById(noteId);
    }

    @PutMapping("/notes/{noteId}")
    public Result<String> noteUpdate(@NotBlank(message = "note不能为空") @RequestParam String noteString,
                                     @NotBlank(message = "noteId不能为空") @PathVariable("noteId") String noteId) {
        return noteService.noteUpdate(noteString, noteId);
    }
}
