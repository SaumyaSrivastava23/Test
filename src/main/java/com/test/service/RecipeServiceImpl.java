package com.test.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.RecipeDao;
import com.test.domain.NewRecipe;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{

	@Autowired private RecipeDao recipeDao;
	
	public int addNewRecipe(NewRecipe newRecipe) {
		
		return this.recipeDao.addNewRecipe(newRecipe);
	}

}
