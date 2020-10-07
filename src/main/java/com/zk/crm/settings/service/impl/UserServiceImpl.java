package com.zk.crm.settings.service.impl;

import com.zk.crm.exception.LoginException;
import com.zk.crm.settings.dao.UserDao;
import com.zk.crm.settings.domain.User;
import com.zk.crm.settings.service.UserService;
import com.zk.crm.utils.DateTimeUtil;
import com.zk.crm.utils.SqlSessionUtil;

public class UserServiceImpl implements UserService{
	private UserDao dao = SqlSessionUtil.getSqlSession().getMapper(UserDao.class);

	@Override
	public User login(String loginAct, String loginPwd, String ip) throws LoginException {
		User user = dao.login(loginAct, loginPwd);
		if(user==null) {
			throw new LoginException("账号密码错误!");
		}
		if(user.getLockState() == "1") {
			throw new LoginException("该账户已经冻结!");
		}
		String sysTime = DateTimeUtil.getSysTime();
		if(user.getExpireTime().compareTo(sysTime) < 0) {
			throw new LoginException("登录已经过期!");
		}
		if(!((user.getAllowIps()).contains(ip))) {
			throw new LoginException("非法ip地址!");
			
		}
		return user;
	}
}









