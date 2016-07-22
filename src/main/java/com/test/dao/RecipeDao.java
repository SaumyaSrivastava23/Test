package com.test.dao;

import java.util.List;

import com.test.domain.NewRecipe;

public interface RecipeDao {
	
	public int addNewRecipe(NewRecipe newRecipe);
	
	public List <NewRecipe> getRecipeList(String userId);
	
	
	public boolean updateNewRecipe(NewRecipe newRecipe);

}
