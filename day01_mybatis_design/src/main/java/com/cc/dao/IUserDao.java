package com.cc.dao;

import com.cc.domain.User;
import com.cc.mybatis.annnotations.Select;

import java.util.List;

/**
 * 用户的持久层接口
 */
public interface IUserDao {
    /**
     * 查询所有
     * @return
     */
    @Select("select * from user")
    List<User> findAll();
}
