package com.springcloud.serverapi.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Set;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 登录用户身份权限
 */
@Data
public class LoginUser implements UserDetails {

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登录时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限列表
     */
    private Set<String> permissions;


    public LoginUser() {
    }

    /**
     * 账户是否未过期,过期无法验证
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     * 
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     * 
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     * 
     * @return
     */
    @JsonIgnore
    @Override
    public boolean isEnabled()
    {
        return true;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
