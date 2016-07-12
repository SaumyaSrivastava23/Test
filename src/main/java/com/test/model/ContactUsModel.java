package com.test.model;

import org.hibernate.validator.constraints.NotEmpty;

public class ContactUsModel {
	
	
	private int contactId;
	
	@NotEmpty(message="{NotEmpty.cuForm.name}")
	private String name;
	
	@NotEmpty(message="{NotEmpty.cuForm.emailId}")
	private String emailId;
	
	@NotEmpty(message="{NotEmpty.cuForm.subject}")
	private String subject;
	
	@NotEmpty(message="{NotEmpty.cuForm.message}")
	private String message;
	
	
	private String contactNo;
	
	
	
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
		
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	} 
	
	
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	
	

}
