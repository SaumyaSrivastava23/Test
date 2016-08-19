package com.test.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.dao.RecipeDao;
import com.test.domain.NewRecipes;


@Service
@Transactional
public class RecipeServiceImpl implements RecipeService{

	
	@Autowired private RecipeDao recipeDao;
	
	public int addNewRecipe(NewRecipes newRecipes) {
		
		return this.recipeDao.addNewRecipe(newRecipes);
	}

	
	public List<NewRecipes> getRecipeList(String userId) {
		
		return this.recipeDao.getRecipeList(userId);
	}

	
	public boolean updateNewRecipe(NewRecipes newRecipes) {
		
		return this.recipeDao.updateNewRecipe(newRecipes);
	}

	
	public NewRecipes getRecipeId(int recipeId) {
	
		return this.recipeDao.getRecipeId(recipeId);
	}

	
}
