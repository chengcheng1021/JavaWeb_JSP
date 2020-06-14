package cn.cc1021.service.impl;

import cn.cc1021.dao.UserDao;
import cn.cc1021.dao.impl.UserDaoImpl;
import cn.cc1021.domain.PageBean;
import cn.cc1021.domain.User;
import cn.cc1021.service.UserService;
import cn.cc1021.util.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        //return dao.findAll();

        //1、从redis中查询
        //1.1 获取 jedis 客户端
        Jedis jedis = JedisPoolUtils.getJedis();
        //1.2 可使用 sortedset 排序查询
        Set<String> allUsers = jedis.zrange("users", 0, -1);

        List<User> users = null;
        //2、判断查询的集合是否为空
        if (allUsers == null || allUsers.size() == 0) {
            //3、如果为空，需要从数据库查询，在将数据存入redis
            UserServiceImpl service = new UserServiceImpl();
            users = service.findAll();

            for (int i=0; i<users.size(); i++) {
                jedis.zadd("users", users.get(i).getId(), users.get(i).getName());
            }

        } else {
            //如果不为空，将 set 数据存入list
            users = new ArrayList<User>();
            for (String name : allUsers) {
                User user = new User();
                user.setName(name);
                users.add(user);
            }
        }

        //4、如果不为空，直接返回
        return users;
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
        if (currentPage <= 1) {
            currentPage = 1;
        }

        //1、创建空的 PageBean 对象
        PageBean<User> pb = new PageBean<User>();

        //2、设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3、调用 dao 查询总记录数
        int totalCount = dao.findTotalCount();
        pb.setTotalCount(totalCount);

        //5、计算总页码
        int totalPage = (totalCount % rows) == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);
        if(currentPage > totalPage){
            currentPage = totalPage;
        }

        //4、调用 dao 查询 List 集合
        //计算开始的记录的索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start, rows);
        pb.setList(list);

        return pb;
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }
}
