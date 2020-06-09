package cn.cc1021.service;

import cn.cc1021.domain.User;

import java.util.List;

/**
 *  用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 新增用户
     * @param user
     */
    void addUser(User user);
}
