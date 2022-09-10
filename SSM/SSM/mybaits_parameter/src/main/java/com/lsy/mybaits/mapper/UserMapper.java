package com.lsy.mybaits.mapper;

import com.lsy.mybaits.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    User getUserByUsername(String username);

    User login(String username, String password);

    User loginByParams(@Param("username") String username, @Param("password") String password);

    Map<String, Object> getUserByMap(@Param("id") Integer id);

    @MapKey("id")
    Map<String, Object> getUserByMapKey();

    List<Map<String, Object>> getAllUserByMap();

    List<User> getUserByLike(@Param("mohu") String mohu);
}
