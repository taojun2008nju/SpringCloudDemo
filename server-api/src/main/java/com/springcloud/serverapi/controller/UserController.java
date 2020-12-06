package com.springcloud.serverapi.controller;

import com.springcloud.common.common.CommonException;
import com.springcloud.common.common.CommonResult;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * User控制类
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login")
    @ResponseBody
    public CommonResult login(String username, String password) {
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                log.error("Method:login exception:", e);
                throw new CommonException("401", "login error");
            } else {
                log.error("Method:login exception:", e);
                throw new CommonException("401", "login error");
            }
        }
        authentication.getPrincipal();
        return new CommonResult("0", "login success", null);
    }

    /**
     * 测试菜单权限
     * @param request
     * @param response
     * @return
     */
    @PreAuthorize("@securityPermissionService.hasPermi('system:admin')")
    @RequestMapping(value = "/testRequir")
    @ResponseBody
    public CommonResult login(HttpServletRequest request, HttpServletResponse response) {
        return new CommonResult("0", "success", null);
    }
}
