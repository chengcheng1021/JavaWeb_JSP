package com.cc.mybatis.sqlsession.defaults;

import com.cc.mybatis.cfg.Configuration;
import com.cc.mybatis.sqlsession.SqlSession;

/**
 * SqlSession 接口的实现类
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;

    public DefaultSqlSession(Configuration cfg){
        this.cfg = cfg;
    }

    /**
     * 用于创建代理对象
     *
     * @param daoInterfaceClass
     * @return
     */
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        return null;
    }

    /**
     * 用于释放资源
     */
    public void close() {

    }
}
