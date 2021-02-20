package com.springcloud.oauth2.service.impl;

import com.springcloud.oauth2.entity.SysUser;
import com.springcloud.oauth2.mapper.UserMapper;
import com.springcloud.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Administrator
 * @date 2021/2/18 21:55:00
 * @description TODO
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = userMapper.findByUsername(username);
        return sysUser;
    }
}
