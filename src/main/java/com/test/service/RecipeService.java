package com.test.service;

import java.util.List;

import com.test.domain.NewRecipe;

public interface RecipeService {
	
	public int addNewRecipe(NewRecipe newRecipe);
	
	public List <NewRecipe> getRecipeList(String userId);
	
	public boolean updateNewRecipe(NewRecipe newRecipe);
	
	public NewRecipe getRecipeId(int recipeId);

}
