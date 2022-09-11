package com.lsy.mybaits.mapper;

import com.lsy.mybaits.pojo.Dept;
import org.apache.ibatis.annotations.Param;

public interface DeptMapper {
    Dept getDeptById(@Param("deptId") Integer deptId);
}
