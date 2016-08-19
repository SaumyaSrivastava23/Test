package com.test.dao;

import java.util.List;

import com.test.domain.NewRecipes;

public interface RecipeDao {
	
	public int addNewRecipe(NewRecipes newRecipes);
	
	
	public List <NewRecipes> getRecipeList(String userId);
	
	
	public boolean updateNewRecipe(NewRecipes newRecipes);
	
	
	public NewRecipes getRecipeId(int recipeId);

	
	
}
