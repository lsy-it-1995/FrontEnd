package com.lsy.mybaits.mapper;

import com.lsy.mybaits.pojo.User;

import java.util.List;

public interface UserMapper {
    int insertUser();
    void updateUser();
    void deleteUser();
    User getUserbById();
    List<User> getAllUser();
}
