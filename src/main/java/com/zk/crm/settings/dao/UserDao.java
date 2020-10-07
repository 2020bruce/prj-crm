package com.zk.crm.settings.dao;

import org.apache.ibatis.annotations.Param;

import com.zk.crm.settings.domain.User;

public interface UserDao {

	User login(@Param("loginAct")String loginAct, @Param("loginPwd")String loginPwd);

}
