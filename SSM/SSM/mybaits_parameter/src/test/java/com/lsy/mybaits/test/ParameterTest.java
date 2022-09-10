package com.lsy.mybaits.test;

import com.lsy.mybaits.mapper.UserMapper;
import com.lsy.mybaits.pojo.User;
import com.lsy.mybaits.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class ParameterTest {

    @Test
    public void testgetUserByUsername(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserByUsername("admin");
        System.out.println(user);
    }

    @Test
    public void testLogin(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String username = "admin",
                password = "123456";
        User user = mapper.login(username, password);
        System.out.println(user);
    }
    @Test
    public void testLoginParams(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        String username = "admin",
                password = "123456";
        User user = mapper.loginByParams(username, password);
        System.out.println(user);
    }
    @Test
    public void testGetUserByMap(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Map<String, Object> map = mapper.getUserByMap(1);
        System.out.println(map);
    }
    @Test
    public void testGetAllUserByMap(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Map<String, Object>> mapList = mapper.getAllUserByMap();
        System.out.println(mapList);
        Map<String, Object> mapByMapKey = mapper.getUserByMapKey();
        System.out.println(mapByMapKey);
    }

    @Test
    public void testGetUserByLike(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.getUserByLike("a");
        users.forEach(System.out::println);
    }
}
