package cn.cc1021.web.Servlet;

import cn.cc1021.domain.User;
import cn.cc1021.service.impl.UserServiceImpl;
import cn.cc1021.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、调用 UserService 完成查询
        UserServiceImpl service = new UserServiceImpl();
        List<User> users = service.findAll();
        //2、将list存入request域
        request.setAttribute("users", users);
        //3、转发到 list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    protected List<User> doPostRedis(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、从redis中查询
        //1.1 获取 jedis 客户端
        Jedis jedis = JedisPoolUtils.getJedis();
        //1.2 可使用 sortedset 排序查询
        Set<String> allUsers = jedis.zrange("users", 0, -1);

        List<User> users = null;
        //2、判断查询的集合是否为空
        if (allUsers == null || allUsers.size() == 0) {
            //3、如果为空，需要从数据库查询，在将数据存入redis
            UserServiceImpl service = new UserServiceImpl();
            users = service.findAll();

            for (int i=0; i<users.size(); i++) {
                jedis.zadd("users", users.get(i).getId(), users.get(i).getName());
            }

        } else {
            //如果不为空，将 set 数据存入list
            users = new ArrayList<User>();
            for (String name : allUsers) {
                User user = new User();
                user.setName(name);
                users.add(user);
            }
        }

        //4、如果不为空，直接返回
        return users;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
