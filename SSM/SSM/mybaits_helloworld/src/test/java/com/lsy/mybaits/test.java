package com.lsy.mybaits;

import com.lsy.mybaits.mapper.UserMapper;
import com.lsy.mybaits.pojo.User;
import com.lsy.mybaits.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.logging.Logger;

public class test {
    @Test
    public void testInsert() throws Exception{
        InputStream input = Resources.getResourceAsStream("mybaits-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(input);

        SqlSession sqlSession = sqlSessionFactory.openSession(true);

//        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//        int res = userMapper.insertUser();

        int res = sqlSession.insert("com.lsy.mybaits.mapper.UserMapper.insertUser");
        System.out.println(res);

//        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testUpdate(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser();
        sqlSession.close();
    }

    @Test
    public void testDelete(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser();
        sqlSession.close();
    }

    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserbById();
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.getAllUser();
        list.forEach(System.out::println);
        sqlSession.close();
    }
}
