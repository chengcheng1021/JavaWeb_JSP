package com.cc.mybatis.sqlsession;

/**
 * 自定义 Mybatis 中和数据库交互的核心类
 * 它里面可以创建 dao 接口的代理对象
 */
public interface SqlSession {

    /**
     * 根据参数创建一个代理对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();
}
