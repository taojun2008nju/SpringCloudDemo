package com.springcloud.oauth2.mapper;

import com.springcloud.oauth2.entity.SysUser;
import java.util.List;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 * @date 2021/2/18 21:54:00
 * @description TODO
 */
public interface UserMapper {

    @Select("select * from sys_user where username=#{username}")
    @Results({
        @Result(id = true, property = "id", column = "id"),
        @Result(property = "roles", column = "id", javaType = List.class,
            many = @Many(select = "com.springcloud.oauth2.mapper.RoleMapper.findByUid"))
    })
    public SysUser findByUsername(String username);
}
