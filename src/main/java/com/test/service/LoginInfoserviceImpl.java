package com.test.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.LoginInfoDao;
import com.test.domain.LoginInfo;


@Service
@Transactional
public class LoginInfoserviceImpl implements LoginInfoService{

	@Autowired private LoginInfoDao loginInfoDao;
	
	public boolean addLoginInfo(LoginInfo loginInfo) {
	
		return this.loginInfoDao.addLoginInfo(loginInfo);
	}

	public boolean updateLoginInfo(LoginInfo loginInfo) {
	
		return this.loginInfoDao.updateLoginInfo(loginInfo);
	}

	public LoginInfo getLoginInfoByUserId(String userId) {
		
		return this.loginInfoDao.getLoginInfoByUserId(userId);
	}

	public boolean resetPassword(String userId, String newPassword) {
		
		return this.loginInfoDao.resetPassword(userId, newPassword);
	}

	public String changePassword(String userId, String oldPassword, String newPassword) {
		
		return this.loginInfoDao.changePassword(userId, oldPassword, newPassword);
	}

}
