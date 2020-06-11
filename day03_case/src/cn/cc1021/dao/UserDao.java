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

    void updateUser(User user);

    /**
     * 查询总记录数
     * @return
     */
    int findTotalCount();

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @return
     */
    List<User> findByPage(int start, int rows);

    /**
     * 登录接口
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);
}
