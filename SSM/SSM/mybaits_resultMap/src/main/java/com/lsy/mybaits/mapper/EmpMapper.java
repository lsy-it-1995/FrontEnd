package com.lsy.mybaits.mapper;

import com.lsy.mybaits.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmpMapper {
    Emp getEmpById(@Param("empId") Integer empId);

    Emp getEmpAndDeptById(@Param("empId") Integer empId);

    List<Emp> getEmpByDeptId(@Param("deptId") Integer deptId);

}
