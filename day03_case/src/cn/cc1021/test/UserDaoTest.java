package cn.cc1021.test;


import cn.cc1021.dao.impl.UserDaoImpl;
import cn.cc1021.domain.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    @Test
    public void testRandomStudy(){
        List<String> strings = new ArrayList<String>(Arrays.asList("golang", "php", "js", "nginx", "mysql", "redis"));
        Random random = new Random();
        System.out.println(strings.get(random.nextInt(strings.size())));
    }
}
