package com.moon.note.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author JinHui
 * @date 2021/8/14
 */

@Repository
public interface PoemDAO {

    @Select("select id,title,poem,time from poems order by time desc")
    List<Map<String,String>> getAllPoem();

    @Insert("insert into poems(title,poem,time) values(#{title},#{poemString},curdate())")
    int savePoem(String title, String poemString);

    @Delete("delete from poems where id = #{ID}")
    int deletePoem(String ID);

}
