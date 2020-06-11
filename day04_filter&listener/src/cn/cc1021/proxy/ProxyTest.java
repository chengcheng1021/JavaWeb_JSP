package cn.cc1021.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

    public static void main(String[] args) {
        //1、创建真实对象
        Lenovo lenovo = new Lenovo();

        //动态代理增强 lenovo 对象
        SaleComputer saleComputer = (SaleComputer) Proxy.newProxyInstance(lenovo.getClass().getClassLoader(), lenovo.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("该方法执行了。。。");
                return null;
            }
        });

        //2、调用方法
        String computer = saleComputer.sale(8000);
        System.out.println(computer);
    }
}
