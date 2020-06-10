package cn.cc1021.service.impl;

import cn.cc1021.dao.UserDao;
import cn.cc1021.dao.impl.UserDaoImpl;
import cn.cc1021.domain.User;
import cn.cc1021.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    /**
     * 新增用户
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    /**
     * 删除用户
     *
     * @param id
     */
    @Override
    public void deleteUser(String id) {
        dao.deleteUser(Integer.parseInt(id));
    }

    @Override
    public User findUser(String id) {
        return dao.findUser(Integer.parseInt(id));
    }

    @Override
    public void updateUser(User user) {
        dao.updateUser(user);
    }

    @Override
    public void delSelectedUser(String[] uids) {
        //1、遍历数组
        for (String id : uids) {
            //2、调用dao删除
            dao.deleteUser(Integer.parseInt(id));
        }

    }
}
