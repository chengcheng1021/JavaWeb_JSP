package cn.cc1021.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

@WebListener
public class ContextLoaderListener implements ServletContextListener {
    /**
     * 监听 ServletContext 对象创建的。ServletContext 对象服务器启动后自动创建。
     *
     * 在服务器启动后自动调用
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //加载资源路径
        //1、获取 ServletContext 对象
        ServletContext servletContext = sce.getServletContext();

        //2、加载资源文件
        String contextConfigLocation = servletContext.getInitParameter("contextConfigLocation");

        //3、获取真实路径
        String realPath = servletContext.getRealPath(contextConfigLocation);

        //4、加载进内存
        try {
            FileInputStream fis = new FileInputStream(realPath);
            System.out.println(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("ServletContext 对象创建了。。。");
    }

    /**
     * 在服务器关闭后，ServletContext 对象被销毁。当服务器正常关闭后该方法被调用
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext 对象被销毁了。。。");
    }
}
