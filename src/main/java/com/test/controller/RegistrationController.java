package com.test.controller;

import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.config.Roles;
import com.test.domain.ContactUs;
import com.test.domain.LoginInfo;
import com.test.domain.Registration;
import com.test.domain.UserRole;
import com.test.model.ContactUsModel;
import com.test.model.RegisterModel;
import com.test.service.ContactUsService;
import com.test.service.LoginInfoService;
import com.test.service.MailService;
import com.test.service.RegisterService;

@Controller
public class RegistrationController {
	
	@Autowired private RegisterService registrationService;
	@Autowired private LoginInfoService loginInfoService;
	@Autowired private ContactUsService contactUsService;
	@Autowired private MailService mailService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public String adminAddEmployee(ModelMap map, HttpServletRequest request, Principal principal)
	{
		
		map.addAttribute("regForm", new RegisterModel());
		return "addUser";
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String adminNewEmployee(@ModelAttribute(value = "regForm") @Valid RegisterModel model,
			BindingResult result, @ModelAttribute(value = "reg") Registration reg, BindingResult regResult,
			@ModelAttribute(value = "login") LoginInfo login, BindingResult loginResult,
			@RequestParam("userId") String userId, ModelMap map, HttpServletRequest request)
	{
		
		System.out.println("userid in controller" + userId);
		boolean st = false;
		try
		{
			Registration user = registrationService.getRegistrationByUserId(userId);
			if (user != null)
			{
				result.addError(new FieldError("regForm", "userId", model.getUserId() , false, new String[1],new String[1], "Email id already registered "));
				st = true;
			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
	        Date date = new Date();
			Date dt = new Date(date.getTime());
			reg.setRegDate(dt);
			try 
			{
				reg.setUserId(model.getUserId());
				login.setUserId(model.getUserId());
					
				login.setIsActive("true");
				
				login.setRegistration(reg);
				UserRole role = new UserRole();
				role.setUserrole(Roles.ROLE_USER);
				role.setLog(login);
				Set<UserRole> roles = new HashSet<UserRole>();
				roles.add(role);
				
				login.setRoles(roles);
				boolean success = loginInfoService.addLoginInfo(login);
				
				Registration reg1 = registrationService.getRegistrationByUserId(userId);
				
				if(reg1 != null)
				{
		            map.addAttribute("status", "success");
		            return "redirect:login";
				}
				
			}
			catch (Exception e) 
			{
				e.printStackTrace();
				return "addUser";
			}
			map.addAttribute("status", "failed");
			return "addUser";
	}
	
	
	
	@RequestMapping(value = "/validateUserId", method = RequestMethod.GET)
	@ResponseBody
	public String validateUserId(ModelMap map, HttpServletRequest request, Principal principal)
	{
		JSONObject obj = new JSONObject();
		
		String eId = request.getParameter("userId");
		if(eId != null && !eId.trim().isEmpty())
		{
			
			Registration reg = registrationService.getRegistrationByEmailId(eId);
			if(reg!= null)
			{
				obj.put("UserId_exist", true);
				return obj.toJSONString();
			}
		}
		obj.put("UserId_exist", false);
		return obj.toJSONString();
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public String contact(ModelMap map, HttpServletRequest request, Principal principal)
	{
		map.addAttribute("cuForm", new ContactUsModel());
		System.out.println("from contact page of index controller");
		return "contact";
	}
	
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public String contactUs(@ModelAttribute(value = "cuForm") @Valid ContactUsModel model,BindingResult result,
			@ModelAttribute(value = "contact") ContactUs contact, ModelMap map, HttpServletRequest request, Principal principal)
	{
		if(result.hasErrors()){
			System.out.println("Invalidate error");
			return "contact";
			
		}
		else{
			Date date = new Date();
			Date dt=new Date(date.getTime());
			contact.setContactDate(dt);
			try{
				contactUsService.addContactDetail(contact);
				String to="noreply.saumya@gmail.com";
				String subject=request.getParameter("subject");
				String content=request.getParameter("message");
				mailService.sendMail(to, null, null, subject, content);
				
			}catch(Exception e){
				
				e.printStackTrace();
				return "contact";
			}
				
		}
		
		return "contact";
	}
	
	
	
}
