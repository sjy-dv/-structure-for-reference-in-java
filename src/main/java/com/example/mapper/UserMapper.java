package com.example.mapper;

import com.example.models.User;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    
    @Select("select * from user")
    List<User> findAll();

    @Insert("insert into user(username, password) values(#{username}, #{password})")
    void create(User user);
}