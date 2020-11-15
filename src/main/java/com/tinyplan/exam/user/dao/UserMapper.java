package com.tinyplan.exam.user.dao;

import com.tinyplan.exam.user.entity.po.User;
import com.tinyplan.exam.user.entity.po.UserDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 获取当前最大的ID
     *
     * @return 最大的ID
     */
    Integer getMaxId();

    /**
     * 查询全部的用户
     *
     * @return 查询得到的用户列表
     */
    List<User> getUsers();

    /**
     * 通过用户名来查找对应的用户
     *
     * @param username 用户名(包括[ 用户ID ]和[ 账户名 ])
     * @return 用户
     */
    User getUserByUsername(@Param("username") String username);

    /**
     * 查找用户详细信息
     *
     * @param userId 用户ID
     * @return 详细信息
     */
    UserDetail getUserDetailByUserId(@Param("userId") String userId);

    /**
     * 增加一个新用户
     */
    Integer insertUser(User user);

    /**
     * 增加用户的新信息
     */
    Integer insertUserDetail(UserDetail detail);

}
