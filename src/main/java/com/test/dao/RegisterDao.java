package com.test.dao;

import com.test.domain.Registration;

public interface RegisterDao {
	
	public Registration getRegistrationByUserId(String userId);
	
	public boolean updateRegistration(Registration registration);
	
	public Registration getRegistrationByEmailId(String eid);

}
