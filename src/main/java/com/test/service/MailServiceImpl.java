package com.test.service;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MailServiceImpl implements MailService{

	@Autowired private JavaMailSender mailSender;
	
	
	public boolean sendMail(String to, String cc, String bcc, String subject, String content) {
		Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        try 
        {
        	Session session = Session.getDefaultInstance(props,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication("noreply.saumya@gmail.com", "Noreply123");
	                    }
	                });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("noreply.saumya@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            if(cc != null && cc.trim().length() > 0)
            {
            	message.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            }
            if(bcc != null && bcc.trim().length() > 0)
            {
            	message.addRecipient(Message.RecipientType.BCC, new InternetAddress(bcc));
            }
            
            message.setSubject(subject);

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(content, "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);


            message.setContent(multipart);

            Transport.send(message);
            return true;
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		return false;

	}

}
