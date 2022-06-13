package com.moon.note.entity;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
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
    @NotNull(message = "标题不能为空")
    String title;
    @NotNull(message = "内容不能为空")
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

    public Note(long noteId, String noteString) {
        String[] titleAndContent = getTitleAndContent(noteString);
        this.id = noteId;
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
