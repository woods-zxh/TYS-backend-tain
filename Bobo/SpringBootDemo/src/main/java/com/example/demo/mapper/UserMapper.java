package com.example.demo.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.example.demo.entity.User;

@Mapper
public interface UserMapper {
    @Select("select * from user where user = #{user} and pass = #{pass}")
    User login(String user, String pass);
    @Insert("insert into user (user, pass) values (#{user}, #{pass})")
    int register(User bean); //Return number of affected lines.

    // The tutorial doesnt define this function,
    // but UserController.java would report an error without this.
    // So I wrote it on my own.
    // Maybe the only part done by me in the whole project.
    @Select("select id from user where user = #{user}")
    User findByUser(String user);
    // After finishing this I found that
    // the author had wrote this function in the end of his article.
    // My only difference is replacing 'id' with '*'.
}
