package com.cc1021.web.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 *  Servlet 的方法
 */
public class ServletDemo2 implements Servlet {
    /**
     * 初始化方法
     * 在 Servlet 被创建时，执行。只会执行一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init...");
    }

    /**
     * 获取 ServletConfig 对象
     * ServletConfig：Servlet 的配置对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 提供服务的方法，每一次 Servlet 被访问时，执行。执行多次
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("service...");
    }

    /**
     * 获取 Servlet 的一些信息，版本，作者等等。。
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     *  销毁方法
     *  在服务器正常关闭时，执行。执行一次
     */
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}
