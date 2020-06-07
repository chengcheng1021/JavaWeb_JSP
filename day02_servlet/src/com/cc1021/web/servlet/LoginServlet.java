package com.cc1021.web.servlet;

import com.cc1021.dao.UserDao;
import com.cc1021.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、设置编码
        request.setCharacterEncoding("utf-8");

        //2、获取请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //3、封装user对象
        User loginUser = new User();
        loginUser.setUsername(username);
        loginUser.setPassword(password);

        //4、调用UserDao的login方法
        UserDao dao = new UserDao();
        User user = dao.login(loginUser);

        //5、判断user

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
