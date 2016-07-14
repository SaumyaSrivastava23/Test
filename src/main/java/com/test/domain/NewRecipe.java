package com.test.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="newrecipe")
public class NewRecipe {
	
	private int recipeId;
	private Registration registration;
	private String recipeTitle;
	private String ingredients;
	private String recipeDetail;
	private String recipeImage;
	private Date createdDate;
	private Date modifiedDate;
	private int purgeFlag;
	
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRecipeId() {
		return recipeId;
	}
	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}
	
	
	@Column
	public String getRecipeTitle() {
		return recipeTitle;
	}
	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}
	
	
	@Lob
	@Column	public String getIngredients() {
		return ingredients;
	}
	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}
	
	
	@Lob
	@Column
	public String getRecipeDetail() {
		return recipeDetail;
	}
	public void setRecipeDetail(String recipeDetail) {
		this.recipeDetail = recipeDetail;
	}
	
	
	@Column
	public String getRecipeImage() {
		return recipeImage;
	}
	public void setRecipeImage(String recipeImage) {
		this.recipeImage = recipeImage;
	}
	
	
	@Column
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	@Column
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
		
	@Column
	public int getPurgeFlag() {
		return purgeFlag;
	}
	public void setPurgeFlag(int purgeFlag) {
		this.purgeFlag = purgeFlag;
	}
	
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="registrationId", referencedColumnName="registrationId")
	public Registration getRegistration() {
		return registration;
	}
	public void setRegistration(Registration registration) {
		this.registration = registration;
	}
	
	
	
	
	
	
	
	

}
