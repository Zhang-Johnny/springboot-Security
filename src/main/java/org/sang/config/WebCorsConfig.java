package org.sang.config;


import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebCorsConfig implements Filter {

    private final List<String> allowedOrigins = Arrays.asList("http://localhost:3000");
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;
        String origin = request.getHeader("Origin");
        System.out.println("*************RequestedSessionId:"+request.getRequestedSessionId());
        //response.setContentType("textml;charset=UTF-8");
        // 配置跨域请求的主机/ip
        response.setHeader("Access-Control-Allow-Origin",  allowedOrigins.contains(origin) ? origin : "*");
        response.setHeader("Vary", "Origin");
        //配置可以写入Cookies
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 配置跨域请求的方法
        response.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,OPTIONS,DELETE");
        // 配置跨域请求的缓冲时间
        response.setHeader("Access-Control-Max-Age", "3600");
        // 配置跨域的 header 头信息
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("XDomainRequestAllowed","1");
        /*response.setHeader("Content-Type","application/json");*/
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void destroy() {
    }

}