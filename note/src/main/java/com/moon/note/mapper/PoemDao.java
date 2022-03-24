package com.moon.note.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @author MoonLight
 */
@Repository
public interface PoemDao {

    @Select("select * from poems limit #{count} OFFSET #{fromIndex}")
    List<HashMap<String, String>> poems(int count, int fromIndex);


    @Insert("insert into poems(sentence,author) value(#{sentence},#{author})")
    void insert(String sentence, String author);

}
