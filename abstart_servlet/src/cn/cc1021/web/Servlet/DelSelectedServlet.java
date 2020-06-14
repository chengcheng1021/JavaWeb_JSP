package cn.cc1021.web.Servlet;

import cn.cc1021.service.UserService;
import cn.cc1021.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取所有ID
        String[] uids = request.getParameterValues("uid");
        //2、调用 service 删除
        UserService service = new UserServiceImpl();
        service.delSelectedUser(uids);

        //3、跳转查询所有 Servlet
        response.sendRedirect(request.getContextPath() + "/userListServlet");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
