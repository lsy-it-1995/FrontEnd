package com.lsy.mybaits.mapper;

import com.lsy.mybaits.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DynamicSQLMapper {
    List<Emp> getEmpByCondition(Emp emp);

    //choose method. if, else if, else if, else...
    List<Emp> getEmpByConditionChoose(Emp emp);

    void insertMultipleEmp(@Param("emps") List<Emp> emps);

    void deleteMultipleEmp(@Param("empIds") Integer[] empIds);
}
