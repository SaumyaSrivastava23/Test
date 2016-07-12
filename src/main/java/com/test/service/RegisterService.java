package com.test.service;

import com.test.domain.Registration;

public interface RegisterService {
	
    public Registration getRegistrationByUserId(String userId);
	
	public boolean updateRegistration(Registration registration);

	public Registration getRegistrationByEmailId(String eid);
}
