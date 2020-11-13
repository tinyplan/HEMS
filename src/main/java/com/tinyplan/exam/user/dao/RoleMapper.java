package com.tinyplan.exam.user.dao;

import com.tinyplan.exam.user.entity.po.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMapper {

    List<Role> getRoles(@Param("roleIdList") List<String> roleIdList);

}
