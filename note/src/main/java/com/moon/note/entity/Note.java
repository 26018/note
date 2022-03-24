package com.moon.note.entity;

import lombok.*;

import java.util.Date;

/**
 * @author JinHui
 * @date 2022/3/12
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    long id;
    String title;
    String content;
    Date time;
    long authorId;
    boolean deleted;

    public Note(String noteString,long authorId) {
        this.authorId = authorId;
        String[] titleAndContent = getTitleAndContent(noteString);
        this.title = titleAndContent[0];
        this.content = titleAndContent[1];
    }

    public Note(long id, String noteString) {
        String[] titleAndContent = getTitleAndContent(noteString);
        this.id = id;
        this.title = titleAndContent[0];
        this.content = titleAndContent[1];
    }

    private String[] getTitleAndContent(String noteString) {
        String[] split = noteString.split("<br>");
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i < split.length; i++) {
            builder.append(split[i]).append('\n');
        }
        return new String[]{split[0], builder.toString()};
    }
}
