package cn.cc1021.web.Filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

//@WebFilter("/*")
public class SensitiveWordsFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1、创建代理对象，增强 getParameter 方法
        ServletRequest proxy_req = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //增强 getParameter 方法
                //判断是否是 getParameter 方法
                if(method.getName().equals("getParameter")){
                    //增强返回值
                    //获取返回值
                    String value = (String) method.invoke(req, args);
                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "***");
                            }
                        }
                    }
                    return value;
                }

                //增强 getParameterMap 方法

                //增强 getParameterValues 方法

                return method.invoke(req, args);
            }
        });

        chain.doFilter(proxy_req, resp);
    }

    private List<String> list = new ArrayList<String>();//敏感词汇集合

    @Override
    public void init(FilterConfig config) throws ServletException {

        try {
            //1、加载文件，获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/敏感词汇.txt");

            //2、读取文件
            BufferedReader br = new BufferedReader(new FileReader(realPath));

            //3、将文件的每一行数据添加到 list 中
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
