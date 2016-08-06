package com.test.dao;

import java.util.List;

import com.test.domain.Category;

public interface CategoryDao {
	
	
    public int addCategory(Category category);
	
	public boolean updateCategory(Category category);
	
	public List<Category> getCategoryList();
	
	public Category getCategory(int categoryId);


}
