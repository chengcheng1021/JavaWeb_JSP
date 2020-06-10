package cn.cc1021.dao;

import cn.cc1021.domain.User;

import java.util.List;

/**
 * 用户操作的Dao
 */
public interface UserDao {

    public List<User> findAll();

    void add(User user);

    void deleteUser(int id);

    User findUser(int id);
}
