package com.cc.mybatis.sqlsession;

import com.cc.mybatis.cfg.Configuration;
import com.cc.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.cc.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * 用于创建一个 SqlSessionFactory 对象
 */
public class SqlSessionFactoryBuilder {

    /**
     * 根据参数的字节输入流来构建一个 SqlSessionFactory 工厂
     * @param config
     * @return
     */
    public SqlSessionFactory build(InputStream config) {
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);
        return new DefaultSqlSessionFactory(cfg);
    }
}
