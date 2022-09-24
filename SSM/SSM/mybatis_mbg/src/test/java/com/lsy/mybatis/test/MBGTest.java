package com.lsy.mybatis.test;

import com.lsy.mybatis.mapper.EmpMapper;
import com.lsy.mybatis.pojo.Emp;
import com.lsy.mybatis.pojo.EmpExample;
import com.lsy.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MBGTest {

    @Test
    public void testMBG(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
//        Emp user = mapper.selectByPrimaryKey(2);
//        EmpExample empExample = new EmpExample();
//        empExample.createCriteria().andAgeEqualTo(25).andGenderEqualTo("m");
//        List<Emp> list = mapper.selectByExample(empExample);
//        list.forEach(System.out::println);
        Emp emp = new Emp(2, "HelloWorld", null, "f");
//        mapper.updateByPrimaryKey(emp);
        mapper.updateByPrimaryKeySelective(emp);
    }

    @Test
    public void testPage(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        EmpMapper empMapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> list = empMapper.selectByExample(null);
        PageHelper.
    }
}
