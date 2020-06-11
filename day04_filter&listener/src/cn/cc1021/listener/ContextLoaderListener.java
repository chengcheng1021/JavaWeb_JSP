package cn.cc1021.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoaderListener implements ServletContextListener {
    /**
     * 监听 ServletContext 对象创建的。ServletContext 对象服务器启动后自动创建。
     *
     * 在服务器启动后自动调用
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
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
