package com.test.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class RegisterModel {
	
	@NotEmpty(message="{NotEmpty.regForm.name}")
    private String name;
	
	@NotEmpty(message="{NotEmpty.regForm.userId}")
	@Email(message="{Email.regForm.userId}")
	private String userId;
	
	@NotEmpty(message="{NotEmpty.regForm.password}")
	@Pattern(regexp="(?=.*\\d)(?=.*[a-z]).{4,20}",message="{Pattern.regForm.password}")
    private String password;
	
	
	@NotNull(message="{NotNull.regForm.confirmPassword}")
	private String confirmPassword;
	
	
	@NotEmpty(message="{NotEmpty.regForm.dob}")
	private String dob;
	
	@NotEmpty(message="{NotEmpty.regForm.userrole}")
	private String userrole;
	
	private Date regDate;

	private String gender;
	
	private String address;

	private String contactNumber;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		matchPassword();
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

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
	

	public String getUserrole() {
		return userrole;
	}

	public void setUserrole(String userrole) {
		this.userrole = userrole;
	}

	private void matchPassword() {
	    if(this.password == null || this.confirmPassword == null){
	        return;
	    }else if(!(this.password.equals(this.confirmPassword))){
	    	System.out.println("in checkpassword");
	        this.confirmPassword = null;
	    }
	}

}
