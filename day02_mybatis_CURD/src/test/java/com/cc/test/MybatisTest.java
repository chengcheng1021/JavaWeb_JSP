package com.cc.test;

import com.cc.dao.IUserDao;
import com.cc.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 测试 Mybatis 的 CRUD 操作
 */
public class MybatisTest {

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() throws IOException {
        //1、读取配置文件，生成字节输入流
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2、获取SqlSessionFactory
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);

        //3、获取SqlSession对象
        SqlSession sqlSession = factory.openSession();

        //4、获取dao的代理对象
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        //5、执行查询所有方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

        //6、释放资源
        sqlSession.close();
        is.close();
    }
}
