package com.test.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

public class NewRecipeModel {
	
	
	private int recipeId;
	
	@NotEmpty(message="{NotEmpty.recForm.recipeTitle}")
	private String recipeTitle;
	
	@NotEmpty(message="{NotEmpty.recForm.recipeDetail}")
	private String recipeDetail;
	
	@NotEmpty(message="{NotEmpty.recForm.ingredients}")
	private String ingredients;
	
	private MultipartFile recipeImage;
	
	
	
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	
	
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	
	
	public String getRecipeDetail() {
		return recipeDetail;
	}
	public void setRecipeDetail(String recipeDetail) {
		this.recipeDetail = recipeDetail;
	}
	
	
	
	public MultipartFile getRecipeImage() {
		return recipeImage;
	}
	public void setRecipeImage(MultipartFile recipeImage) {
		this.recipeImage = recipeImage;
	}

	
	
	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	
}
