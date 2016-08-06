package com.test.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.domain.Category;

@Repository
public class CategoryDaoImpl implements CategoryDao{

	
	@Autowired private SessionFactory sessionFactory;
	
	public int addCategory(Category category) {
		
		this.sessionFactory.getCurrentSession().save(category);
		this.sessionFactory.getCurrentSession().flush();
		return category.getCategoryId();
	}

	
	public boolean updateCategory(Category category) {
		
		try {
			
			this.sessionFactory.getCurrentSession().update(category);
			this.sessionFactory.getCurrentSession().flush();
			return true;
			
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Category> getCategoryList() {
		
		return this.sessionFactory.getCurrentSession().createCriteria(Category.class)
				.add(Restrictions.isNull("deleteDate")).list();
	
	}


	public Category getCategory(int categoryId) {
		
		return (Category) this.sessionFactory.getCurrentSession().get(Category.class, categoryId);
	}
	
}
