package com.test.dao;

import org.hibernate.SessionFactory;
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

}
