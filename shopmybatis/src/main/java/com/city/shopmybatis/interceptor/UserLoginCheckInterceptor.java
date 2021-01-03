package com.city.shopmybatis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
//用户登录检查过滤器
public class UserLoginCheckInterceptor implements HandlerInterceptor {

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
       
		System.out.println("登录检查拦截器预处理....");
		HttpSession session=request.getSession();
		System.out.println(session.getAttribute("user"));
		if(session==null||session.getAttribute("user")==null) {
			response.setStatus(50012);
			return false;
		}
		else {
			return true;
		}
    }
}
