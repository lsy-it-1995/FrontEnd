package com.lsy.mybaits.mapper;

import com.lsy.mybaits.pojo.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DeptMapper {
    Dept getDeptById(@Param("deptId") Integer deptId);

    Dept getDeptAndEmpByDeptID(@Param("deptId") Integer deptId);

    Dept getDeptAndEmpByDeptIdStep(@Param("deptId") Integer deptId);
}
