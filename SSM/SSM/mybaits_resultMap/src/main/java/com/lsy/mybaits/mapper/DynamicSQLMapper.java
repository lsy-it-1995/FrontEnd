package com.lsy.mybaits.mapper;

import com.lsy.mybaits.pojo.Emp;

import java.util.List;

public interface DynamicSQLMapper {
    List<Emp> getEmpByCondition(Emp emp);
}
