package com.test.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.ContactUsDao;
import com.test.domain.ContactUs;


@Service
@Transactional
public class ContactUsServiceImpl implements ContactUsService{

	
	@Autowired private ContactUsDao contactUsDao;
	public int addContactDetail(ContactUs contactUs) {
	
		return this.contactUsDao.addContactDetail(contactUs);
	}

}
