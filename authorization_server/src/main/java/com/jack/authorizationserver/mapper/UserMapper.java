package com.jack.authorizationserver.mapper;

import com.jack.authorizationserver.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users")
    List<User> getAllUsers();

    @Select("select * from users where name=#{name}")
    public User queryUserByName(String name);

    @Select("SELECT code FROM permissions WHERE id IN(" +
            "SELECT permission_id FROM role_permission WHERE role_id IN(" +
            "SELECT role_id FROM user_role WHERE user_id = #{id}))")
    List<String> findPermissionByUserId(int id);
}
