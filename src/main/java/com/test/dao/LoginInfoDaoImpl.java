package com.test.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.test.domain.LoginInfo;

@Repository
public class LoginInfoDaoImpl implements LoginInfoDao{

	@Autowired private SessionFactory sessionFactory;
	
	public boolean addLoginInfo(LoginInfo loginInfo) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		loginInfo.setPassword(encoder.encode(loginInfo.getPassword()));
		try 
		{
			this.sessionFactory.getCurrentSession().save(loginInfo);
			this.sessionFactory.getCurrentSession().flush();
			loginInfo.getRegistration().setLog(loginInfo);
			this.sessionFactory.getCurrentSession().save(loginInfo.getRegistration());
			this.sessionFactory.getCurrentSession().flush();
			return true;
		}
		catch (Exception e) 
		{
			try
			{
				this.sessionFactory.getCurrentSession().delete(loginInfo.getRegistration());
				this.sessionFactory.getCurrentSession().delete(loginInfo);
				e.printStackTrace();
			}
			catch (Exception e2) 
			{
				e2.printStackTrace();
			}
		}
		return false;
	}

	public boolean updateLoginInfo(LoginInfo loginInfo) {
		try
		{
			this.sessionFactory.getCurrentSession().update(loginInfo);;
			this.sessionFactory.getCurrentSession().flush();
			return true;
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public LoginInfo getLoginInfoByUserId(String userId) {
		List<LoginInfo> list= this.sessionFactory.getCurrentSession().createCriteria(LoginInfo.class)
				.add(Restrictions.eq("userId", userId))
				.list();
		if(!list.isEmpty()){
			
			return list.get(0);
		}
				
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public boolean resetPassword(String userId, String newPassword) {
		try{
			
			List<LoginInfo> list= this.sessionFactory.getCurrentSession().createCriteria(LoginInfo.class)
					.add(Restrictions.eq("userId", userId))
					.list();
			if(!list.isEmpty()&& list!=null){
				
				LoginInfo loginInfo=list.get(0);
				loginInfo.setForgotPwdId(null);
				BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
				String hashedPassword=passwordEncoder.encode(newPassword);
				loginInfo.setPassword(hashedPassword);
				this.sessionFactory.getCurrentSession().update(loginInfo);
				this.sessionFactory.getCurrentSession().flush();
								
			}
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public String changePassword(String userId, String oldPassword, String newPassword) {
		String status="";
	try{
		List<LoginInfo> list= this.sessionFactory.getCurrentSession().createCriteria(LoginInfo.class)
				.add(Restrictions.eq("userId", userId))
				.list();
		if(!list.isEmpty()&& list!=null){
			LoginInfo loginInfo=list.get(0);
			if(BCrypt.checkpw(oldPassword, loginInfo.getPassword())){
				BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
				String hashedpassword=passwordEncoder.encode(newPassword);
				loginInfo.setPassword(hashedpassword);
				this.sessionFactory.getCurrentSession().update(loginInfo);
				this.sessionFactory.getCurrentSession().flush();
				return "success";
			  }
					return "not_match";
				}
				
		      status= "not_exist";
	}
	catch(Exception e){
		e.printStackTrace();
	}
		return status;
	}

}
