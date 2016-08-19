package com.test.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="registration")
public class Registration {
	
	private int registrationId;
    
	private String name;
	
	private String userId;
	
	private String dob;
	
	private String contactNumber;
	
	private Date regDate;

	private Date modificationDate;
	
	private String gender;
	
	private String address;
	
	private int purgeFlag;
	
	private LoginInfo log;
	
	
	private Set <NewRecipes> newRecipes = new HashSet<NewRecipes>();
	
	@Id
	@Column(nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(int registrationId) {
		this.registrationId = registrationId;
	}

	@Column(nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(nullable=false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Column
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Column
	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	@Column
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Column
	public int getPurgeFlag() {
		return purgeFlag;
	}

	public void setPurgeFlag(int purgeFlag) {
		this.purgeFlag = purgeFlag;
	}

	
	@Column
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	
	@OneToOne(fetch = FetchType.LAZY) 
    @JoinColumn(name="loginId" , referencedColumnName="loginId") 
	public LoginInfo getLog() {
		return log;
	}

	public void setLog(LoginInfo log) {
		this.log = log;
	}

		
	
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL, mappedBy="registration")
	public Set<NewRecipes> getNewRecipes() {
		return newRecipes;
	}

	public void setNewRecipes(Set<NewRecipes> newRecipes) {
		this.newRecipes = newRecipes;
	}

	
}
