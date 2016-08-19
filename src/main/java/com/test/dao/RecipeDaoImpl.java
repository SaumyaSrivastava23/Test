package com.test.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.domain.NewRecipes;

@Repository
public class RecipeDaoImpl implements RecipeDao{

	@Autowired private SessionFactory sessionFactory;
	
	public int addNewRecipe(NewRecipes newRecipes) {
		
		this.sessionFactory.getCurrentSession().save(newRecipes);
		this.sessionFactory.getCurrentSession().flush();
		return newRecipes.getRecipeId();
	}

	
	@SuppressWarnings("unchecked")
	public List<NewRecipes> getRecipeList(String userId) {
		
		try{
			
			List <NewRecipes> list= this.sessionFactory.getCurrentSession().createCriteria(NewRecipes.class)
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


	public boolean updateNewRecipe(NewRecipes newRecipes) {
		try {
			
			this.sessionFactory.getCurrentSession().update(newRecipes);
			this.sessionFactory.getCurrentSession().flush();
			return true;
			
		} catch (Exception e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}


	public NewRecipes getRecipeId(int recipeId) {
	
		return (NewRecipes) this.sessionFactory.getCurrentSession().get(NewRecipes.class,recipeId);
	}

}
