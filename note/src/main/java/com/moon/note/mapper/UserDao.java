package com.moon.note.mapper;

import com.moon.note.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author JinHui
 * @date 2022/3/14
 */

@Repository
public interface UserDao {

    @Select("select id,username, AES_DECRYPT(password, 'lsk-art') as password  from users where username = #{username}")
    User selectUserByUsername(String username);

    @ResultType(Boolean.class)
    @Insert("insert users(username,password) values(#{username},AES_ENCRYPT((#{password}),'lsk-art'))")
    Boolean insertUser(String username,String password);

    @ResultType(Boolean.class)
    Boolean userChange();

}
