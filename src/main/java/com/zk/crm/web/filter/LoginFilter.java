package com.zk.crm.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zk.crm.settings.domain.User;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("判断有没有登录!");
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String path = request.getServletPath();
		if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)) {
			chain.doFilter(req, resp);
		}else {
			User user = (User)request.getSession().getAttribute("user");
			if(user != null) {
				chain.doFilter(req, resp);
			}else {
				response.sendRedirect(request.getContextPath() + "/index.html");
			}
		}
		
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
