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
 * mybatis入门案例
 */
public class MybatisTest {

    /**
     * 入门案例
     * @param args
     */
    @Test
    public static void main(String[] args) throws IOException {
        //1、读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2、创建 SqlSessionFactory 工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);

        //3、使用工厂生产 SqlSession 对象
        SqlSession session = factory.openSession();

        //4、使用 SqlSession 创建 Dao 接口的代理对象
        IUserDao userDao = session.getMapper(IUserDao.class);

        //5、使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }

        //6、释放资源
        session.close();
        is.close();
    }
}
