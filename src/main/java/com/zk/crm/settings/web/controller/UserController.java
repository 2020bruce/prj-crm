package com.zk.crm.settings.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zk.crm.settings.domain.User;
import com.zk.crm.settings.service.UserService;
import com.zk.crm.settings.service.impl.UserServiceImpl;
import com.zk.crm.utils.MD5Util;
import com.zk.crm.utils.PrintJson;
import com.zk.crm.utils.ServiceFactory;

public class UserController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if("/settings/user/login.do".equals(path)) {
			login(request, response);
		}else {
			
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		
		String loginAct = request.getParameter("loginAct");
		String loginPwd = request.getParameter("loginPwd");
		loginPwd = MD5Util.getMD5(loginPwd);
		String ip = request.getRemoteAddr();
		try {
			UserService service = (UserService)ServiceFactory.getService(new UserServiceImpl());
			User user = service.login(loginAct, loginPwd, ip);
			request.getSession().setAttribute("user", user);
			PrintJson.printJsonFlag(response, true);
		} catch (Exception e) {
			//e.printStackTrace();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("success", false);
			map.put("msg", e.getMessage());
			PrintJson.printJsonObj(response, map);
			
		}
		
	}

}











