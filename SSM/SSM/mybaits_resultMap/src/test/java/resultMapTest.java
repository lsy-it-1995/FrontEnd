import com.lsy.mybaits.mapper.DeptMapper;
import com.lsy.mybaits.mapper.DynamicSQLMapper;
import com.lsy.mybaits.mapper.EmpMapper;
import com.lsy.mybaits.pojo.Dept;
import com.lsy.mybaits.pojo.Emp;
import com.lsy.mybaits.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class resultMapTest {
    @Test
    public void testgetEmpById(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp user = mapper.getEmpById(2);
        System.out.println(user);
    }

    @Test
    public void testgetEmpAndDeptById(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp user = mapper.getEmpAndDeptById(2);
        System.out.println(user);
    }

    @Test
    public void getEmpAndDeptById(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp user = mapper.getEmpAndDeptById(1);
        System.out.println(user);
    }

    @Test
    public void getDeptAndEmpByDeptId(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
//        Dept dept = mapper.getDeptAndEmpByDeptID(1);
        Dept dept = mapper.getDeptAndEmpByDeptIdStep(1);
        System.out.println(dept);
    }

    @Test
    public void getGetEmpByCondition(){
        SqlSession sqlSession = SqlSessionUtils.getSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
//        Dept dept = mapper.getDeptAndEmpByDeptID(1);
        Emp emp = new Emp(null, "six", 26, "f");
        List<Emp> list = mapper.getEmpByCondition(emp);
        list.forEach(System.out::println);
    }
}
