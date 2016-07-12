package com.test.service;

public interface MailService {
	
	public boolean sendMail(String to,String cc,String bcc,String subject, String content);

}
