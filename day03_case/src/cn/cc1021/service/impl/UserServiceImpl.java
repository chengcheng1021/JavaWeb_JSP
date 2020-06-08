package cn.cc1021.service.impl;

import cn.cc1021.domain.User;
import cn.cc1021.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return null;
    }
}
