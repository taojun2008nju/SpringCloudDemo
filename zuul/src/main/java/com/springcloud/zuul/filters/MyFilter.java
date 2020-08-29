package com.springcloud.zuul.filters;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyFilter extends ZuulFilter {

    /**
     * Filter类型
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * filter的执行顺序
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * 是否要拦截(启动)
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * Filter的具体业务逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        // ThreadLocal
        RequestContext requestContext = RequestContext.getCurrentContext();

        HttpServletRequest request = requestContext.getRequest();
        HttpServletResponse response = requestContext.getResponse();

        boolean tokenValid = false;
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headName = headerNames.nextElement();
            log.info("HeadName:{}, HeadValue:{}", headName, request.getHeader(headName));
            if (headName.equalsIgnoreCase("token") && request.getHeader("token").equalsIgnoreCase("tao")) {
                tokenValid = true;
            }
        }
        if (!tokenValid) {
            renderJson(requestContext, response, "Token验证失败");
        }
        return null;
    }

    /**
     * 渲染json对象
     */
    public static void renderJson(RequestContext ctx, HttpServletResponse response, Object jsonObject) {
        // 设置终止请求
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(JSON.toJSONString(jsonObject));
    }
}
