package cn.cc1021.dao.impl;

import cn.cc1021.dao.UserDao;
import cn.cc1021.domain.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        return null;
    }
}
