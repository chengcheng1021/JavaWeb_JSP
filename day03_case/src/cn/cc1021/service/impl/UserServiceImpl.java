package cn.cc1021.service.impl;

import cn.cc1021.dao.UserDao;
import cn.cc1021.dao.impl.UserDaoImpl;
import cn.cc1021.domain.PageBean;
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

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //1、创建空的 PageBean 对象
        PageBean<User> pb = new PageBean<User>();

        //2、设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3、调用 dao 查询总记录数
        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);

        //4、调用 dao 查询 List 集合
        //计算开始的记录的索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start, rows);
        pb.setList(list);

        //5、计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);

        return pb;
    }
}
