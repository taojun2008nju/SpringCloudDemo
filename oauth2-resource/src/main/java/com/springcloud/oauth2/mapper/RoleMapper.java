package com.springcloud.oauth2.mapper;

import com.springcloud.oauth2.entity.SysRole;
import java.util.List;
import org.apache.ibatis.annotations.Select;

/**
 * @author Administrator
 * @date 2021/2/18 21:54:00
 * @description TODO
 */
public interface RoleMapper {

    @Select("select r.id,r.role_name roleName ,r.role_desc roleDesc " +
        "FROM sys_role r,sys_user_role ur " +
        "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    public List<SysRole> findByUid(Integer uid);
}
