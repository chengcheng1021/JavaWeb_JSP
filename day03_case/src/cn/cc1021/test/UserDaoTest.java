package cn.cc1021.test;


import cn.cc1021.dao.impl.UserDaoImpl;
import cn.cc1021.domain.User;
import org.junit.Test;

import java.util.List;

public class UserDaoTest {

    @Test
    public void testLogin(){
        User loginUser = new User();
        loginUser.setId(1);

        UserDaoImpl dao = new UserDaoImpl();
        User user = dao.login(loginUser);

        System.out.println(user);
    }

    @Test
    public void testFindAll(){
        UserDaoImpl dao = new UserDaoImpl();
        List<User> all = dao.findAll();
        System.out.println(all);

    }
}
