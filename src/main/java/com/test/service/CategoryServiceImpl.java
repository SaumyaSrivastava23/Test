package com.test.service;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.test.dao.CategoryDao;
import com.test.domain.Category;


@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{

	
	@Autowired private CategoryDao categoryDao;
	
	
	public int addCategory(Category category) {
		
		return this.categoryDao.addCategory(category);
	}

	
	public boolean updateCategory(Category category) {
		
		return this.categoryDao.updateCategory(category);
	}

	
	public List<Category> getCategoryList() {
		
		return this.categoryDao.getCategoryList();
	}

	
	public Category getCategory(int categoryId) {
		
		return this.categoryDao.getCategory(categoryId);
	}
	
	

}
