package com.zk.crm.settings.service;

import com.zk.crm.exception.LoginException;
import com.zk.crm.settings.domain.User;

public interface UserService {

	User login(String loginAct, String loginPwd, String ip) throws LoginException;

}
