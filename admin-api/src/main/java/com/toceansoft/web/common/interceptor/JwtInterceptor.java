package com.toceansoft.web.common.interceptor;

import cn.hutool.json.JSONUtil;
import com.toceansoft.common.util.JWTUtils;
import com.toceansoft.common.util.R;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * jwt拦截器
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        System.out.println("------------------------------拦截器");
//        String uri =request.getRequestURI();//
//        if(uri.equals("/api/admin/login")||uri.equals("/api/admin/logout")){
//            return true;//不拦截
//        }
//        String token=request.getHeader("X-Token");
//        if(JWTUtils.checkToken(token)){//校验token
//            return true;
//        }
//        R r=R.fail(50008,"token无效");
//        response.getWriter().println(JSONUtil.toJsonStr(r));
//        return false;
        /**
         * 暂时先不拦截，便于后台post测试
         */
        return true;
    }
}
