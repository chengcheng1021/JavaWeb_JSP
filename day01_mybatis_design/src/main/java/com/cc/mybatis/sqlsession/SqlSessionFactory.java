package com.cc.mybatis.sqlsession;

public interface SqlSessionFactory {

    /**
     * 用于打开一个新的 SqlSession 对象
     * @return
     */
    public SqlSession openSession();
}
