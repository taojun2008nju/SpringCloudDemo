package com.springcloud.serverapi.security;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2020/12/6 19:17:00
 * @description TODO
 */
@Component
public class SecutiryUserDetailsService implements UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里可以可以通过username（登录时输入的用户名）然后到数据库中找到对应的用户信息，并构建成我们自己的UserInfo来返回。
        //由于权限参数不能为空，所以这里先使用AuthorityUtils.commaSeparatedStringToAuthorityList方法模拟一个admin的权限，该方法可以将逗号分隔的字符串转换为权限集合。
        //数据库中的密码是加密后的
        LoginUser loginUser = new LoginUser();
        loginUser.setUsername(username);
        if ("admin".equalsIgnoreCase(username)) {
            loginUser.setPassword(bCryptPasswordEncoder.encode("admin"));
            Set<String> permissions = new HashSet<>(Arrays.asList("system:admin"));
            loginUser.setPermissions(permissions);
        } else {
            loginUser.setPassword(bCryptPasswordEncoder.encode("123456"));
            Set<String> permissions = new HashSet<>(Arrays.asList("system:worker"));
            loginUser.setPermissions(permissions);
        }
        return loginUser;
    }
}
