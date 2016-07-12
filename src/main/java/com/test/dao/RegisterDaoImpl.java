package com.test.dao;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.domain.Registration;

@Repository
public class RegisterDaoImpl implements RegisterDao{
	
	@Autowired private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public Registration getRegistrationByUserId(String userId) {
		
		List<Registration> list = this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.createAlias("log", "logAlias")
				.add(Restrictions.eq("logAlias.userId", userId))
				.setFetchMode("log", FetchMode.JOIN)
				.list();
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		
		
		
		return null;
	}

	public boolean updateRegistration(Registration registration) {
		try{
			
			this.sessionFactory.getCurrentSession().update(registration);
			this.sessionFactory.getCurrentSession().flush();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public Registration getRegistrationByEmailId(String eid) {
		List<Registration> list = this.sessionFactory.getCurrentSession().createCriteria(Registration.class)
				.add(Restrictions.eq("userId", eid))
				.setMaxResults(1)
				.list();
		if(!list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}

}
