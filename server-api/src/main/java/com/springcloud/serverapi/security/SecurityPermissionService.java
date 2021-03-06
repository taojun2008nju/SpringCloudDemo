package com.springcloud.serverapi.security;

import com.springcloud.serverapi.util.JwtUtils;
import com.springcloud.serverapi.util.ServletUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 * @date 2020/12/6 20:27:00
 * @description spring security 权限验证逻辑服务实现类
 */
@Service("securityPermissionService")
public class SecurityPermissionService {

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 验证用户是否具备某权限
     *
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     */
    public boolean hasPermi(String permission) {
        if (StringUtils.isEmpty(permission)) {
            return false;
        }
        LoginUser loginUser = jwtUtils.getLoginUser(ServletUtils.getRequest());
        if (null == loginUser) {
            return false;
        }
        if (("system:" + loginUser.getUsername()).equalsIgnoreCase(permission)){
            return true;
        } else {
            return false;
        }
    }
}
