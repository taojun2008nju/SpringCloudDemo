package com.springcloud.serverapi.security.handle;

import com.alibaba.fastjson.JSON;
import com.springcloud.common.common.CommonResult;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * 自定义退出处理类 返回成功
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    /**
     * 退出处理
     * 
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        CommonResult commonResult = new CommonResult("0", "logout success", null);
        renderString(response, JSON.toJSONString(commonResult));
    }

    /**
     * 渲染json对象
     */
    public static String renderString(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
