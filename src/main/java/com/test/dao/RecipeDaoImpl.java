package com.test.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.domain.NewRecipe;

@Repository
public class RecipeDaoImpl implements RecipeDao{

	@Autowired private SessionFactory sessionFactory;
	
	public int addNewRecipe(NewRecipe newRecipe) {
		this.sessionFactory.getCurrentSession().save(newRecipe);
		this.sessionFactory.getCurrentSession().flush();
		return newRecipe.getRecipeId();
	}

	
	@SuppressWarnings("unchecked")
	public List<NewRecipe> getRecipeList(String userId) {
		
		try{
			
			List <NewRecipe> list= this.sessionFactory.getCurrentSession().createCriteria(NewRecipe.class)
					.createAlias("registration", "reg")
					.add(Restrictions.eq("reg.userId", userId))
					.addOrder(Order.desc("createdDate"))
					.list();
			
			return list;
		
		}
		catch(Exception e)
		{
		    
			e.printStackTrace();
		}
		
		
		return null;
	}


	public boolean updateNewRecipe(NewRecipe newRecipe) {
		try {
			
			this.sessionFactory.getCurrentSession().update(newRecipe);
			this.sessionFactory.getCurrentSession().flush();
			return true;
			
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}

}
