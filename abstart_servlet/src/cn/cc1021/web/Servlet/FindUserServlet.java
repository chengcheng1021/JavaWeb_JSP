package cn.cc1021.web.Servlet;

import cn.cc1021.domain.User;
import cn.cc1021.service.UserService;
import cn.cc1021.service.impl.UserServiceImpl;
import redis.clients.jedis.JedisPoolConfig;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取ID
        String id = request.getParameter("id");
        //调用获取接口
        UserService service = new UserServiceImpl();
        User user = service.findUser(id);
        request.setAttribute("user", user);

        request.getRequestDispatcher("/update.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
