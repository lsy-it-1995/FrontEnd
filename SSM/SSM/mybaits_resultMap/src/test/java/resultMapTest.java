import com.lsy.mybaits.mapper.EmpMapper;
import com.lsy.mybaits.pojo.Emp;
import com.lsy.mybaits.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

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
}
