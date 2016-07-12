package com.test.service;

import com.test.domain.LoginInfo;

public interface LoginInfoService {
	
	public boolean addLoginInfo(LoginInfo loginInfo);
	
    public boolean updateLoginInfo(LoginInfo loginInfo);
	
	public LoginInfo getLoginInfoByUserId(String userId);
	
	public boolean resetPassword(String userId, String newPassword);
	
	public String changePassword(String userId, String oldPassword, String newPassword);

}
