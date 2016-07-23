package com.test.service;

import java.util.List;

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

	public List<NewRecipe> getRecipeList(String userId) {
		
		return this.recipeDao.getRecipeList(userId);
	}

	public boolean updateNewRecipe(NewRecipe newRecipe) {
		
		return this.recipeDao.updateNewRecipe(newRecipe);
	}

	public NewRecipe getRecipeId(int recipeId) {
	
		return this.recipeDao.getRecipeId(recipeId);
	}

}
