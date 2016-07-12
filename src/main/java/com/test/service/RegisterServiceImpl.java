package com.test.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.RegisterDao;
import com.test.domain.Registration;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService{

	
	@Autowired private RegisterDao registerDao;

	public Registration getRegistrationByUserId(String userId) {
		
		return this.registerDao.getRegistrationByUserId(userId);
	}

	public boolean updateRegistration(Registration registration) {
	
		return this.registerDao.updateRegistration(registration);
	}

	public Registration getRegistrationByEmailId(String eid) {
	
		return this.registerDao.getRegistrationByEmailId(eid);
	}
}
