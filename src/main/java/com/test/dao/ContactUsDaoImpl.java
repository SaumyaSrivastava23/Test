package com.test.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.domain.ContactUs;


@Repository
public class ContactUsDaoImpl implements  ContactUsDao{

	
	@Autowired private SessionFactory sessionFactory;
	public int addContactDetail(ContactUs contactUs) {
		
		this.sessionFactory.getCurrentSession().save(contactUs);
		this.sessionFactory.getCurrentSession().flush();
		return contactUs.getContactId();
	}

}
