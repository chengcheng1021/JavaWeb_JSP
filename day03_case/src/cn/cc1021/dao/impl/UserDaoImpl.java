package cn.cc1021.dao.impl;

import cn.cc1021.dao.UserDao;
import cn.cc1021.domain.User;
import cn.cc1021.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1、定义 sql
        String sql = "select * from users";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return user包含用户全部数据，没有查询到，返回 null
     */
    public User login(User loginUser){
        try {
            //1、编写sql
            String sql = "select * from users where id = ?";
            //2、调用query方法
            User user = template.queryForObject(sql,
                    new BeanPropertyRowMapper<User>(User.class),
                    loginUser.getId());

            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();//记录日志
            return null;
        }
    }

    @Override
    public void add(User user) {
        //1、定义 sql
        String sql = "insert into users values(null, ?, ?, ?, ?, ?, ?)";
        //2、执行 sql
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    public void deleteUser(int id) {
        //1、定义 sql
        String sql = "delete from users where id = ?";
        //2、执行 sql
        template.update(sql, id);
    }

    @Override
    public User findUser(int id) {
        //1、定义 sql
        String sql = "select * from users where id = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
        return user;
    }

    @Override
    public void updateUser(User user) {
        //1、定义 sql
        String sql = "update users set name = ?, gender = ?, age = ?, address = ?, qq = ?, email = ? where id = ?";
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    /**
     * 查询总记录数
     *
     * @return
     */
    @Override
    public int findTotalCount() {
        String sql = "select count(*) from users";

        return template.queryForObject(sql, Integer.class);
    }

    /**
     * 分页查询每页记录
     *
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<User> findByPage(int start, int rows) {
        String sql = "select * from users limit ?, ?";

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, rows);
    }
}
