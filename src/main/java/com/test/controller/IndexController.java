package com.test.controller;

import java.security.Principal;
import java.util.TimeZone;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.test.config.ProjectConfig;
import com.test.config.Roles;
import com.test.config.Validation;
import com.test.domain.LoginInfo;
import com.test.domain.Registration;
import com.test.service.LoginInfoService;
import com.test.service.MailService;
import com.test.service.RegisterService;


@Controller
public class IndexController {
	
	@Autowired private RegisterService registrationService;
	@Autowired private LoginInfoService loginInfoService;	
	@Autowired private MailService mailService;
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(ModelMap map, HttpServletRequest request, Principal principal)
	{
		
		System.out.println("from index page of index controller");
		return "index";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(ModelMap map, HttpServletRequest request, Principal principal)
	{
		
		System.out.println("from login page of index controller");
		return "login";
	}

	
	
	@RequestMapping(value = "/getLogedIn", method = RequestMethod.GET)
	public String getLogedIn(ModelMap map, HttpServletRequest request, Principal principal, TimeZone timeZone)
	{
		Registration reg = registrationService.getRegistrationByUserId(principal.getName());
		if(reg != null)
		{
			request.getSession(true).setAttribute("registration", reg);
			return "redirect:userHome";
		}
		return "redirect:login";
	}
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return redirect to the index page after  login failed
	 */
	@RequestMapping(value = "/failtologin", method = RequestMethod.GET)
	public String failtologin(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("from failtologin page of index controller");
		String error="true";
		return "redirect:/login?error="+error;
		
	}
	
	
	/**
	 * Destroy user session and delete cookies for this JSESSIONID
	 * @param map
	 * @param request
	 * @param principal
	 * @return redirect to the index page after  login failed
	 */
	
	@RequestMapping(value = "/insertLogOut", method = RequestMethod.GET)
	public @ResponseBody String insertLogOut(ModelMap map, HttpServletRequest request)
	{
		System.out.println("from logout page");
		return "logedOut";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("from logout successfully");
		
		return "redirect:/login";
		
	}
	
	
	/**
	 * @param map
	 * @param request
	 * @param principal
	 * @return It redirect to dashboard page based on user loged in role. Like for admin, it redirect to admin dashboard.
	 */
	
	@RequestMapping(value = "/userHome", method = RequestMethod.GET)
	public String userHome(ModelMap map, HttpServletRequest request, Principal principal)
	{
		if(request.isUserInRole(Roles.ROLE_ADMIN))
		{
			return "redirect:adminDashboard";
		}
		else if(request.isUserInRole(Roles.ROLE_MANAGER))
		{
			return "redirect:index";
		}
		else
		{
			return "redirect:index";
		}
	}
	
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(ModelMap map, HttpServletRequest request, Principal principal)
	{
		return "error";
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public String forgotPassword(ModelMap map, HttpServletRequest request, Principal principal)
	{
		return "forgotPassword";
	}
	
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public String forgot_Password(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String userId = request.getParameter("userId");
		if(userId != null && userId.length() > 0 && Validation.validateEmail(userId.trim()))
		{
			Registration reg = registrationService.getRegistrationByUserId(userId);
			LoginInfo info = loginInfoService.getLoginInfoByUserId(userId);
			if(reg != null && info != null)
			{
				String uuid = UUID.randomUUID().toString();
				
				info.setForgotPwdId(uuid);
				loginInfoService.updateLoginInfo(info);
				String url = ProjectConfig.url+"/resetPassword?email="+userId+"&token="+uuid;
				System.out.println("url : " + url);
				String subject = "Reset Password from Test";
				String content = "Dear "+ reg.getName()+", <br><br>"
						+ "Please click on link below to reset password <br>"
						+ "<br>"
						+ "<a href='http://"+ url +"'>"+url+"</a>";
				
				mailService.sendMail(userId, null, null, subject, content);
				map.addAttribute("resetPwd", "true");
				map.addAttribute("resetMsg", "Password reset link send to your email id. Please reset password from that link !");
				return "forgotPassword";
			}
			map.addAttribute("userId", userId);
			map.addAttribute("resetPwd", "false");
			map.addAttribute("resetMsg", "User Id not found !");
			return "forgotPassword";
			
		}
		map.addAttribute("userId", userId);
		map.addAttribute("resetPwd", "false");
		map.addAttribute("resetMsg", "Invalid user id !");
		return "forgotPassword";
		
	}
	
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public String resetPassword(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		if(email != null && email.length() > 0 && token != null && token.length() > 0 )
		{
			LoginInfo info = loginInfoService.getLoginInfoByUserId(email);
			if(info != null && info.getForgotPwdId().equals(token))
			{
				map.addAttribute("email", email);
				map.addAttribute("token", token);
				return "resetPassword";
			}
		}
		return "redirect:forgotPassword";
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public String reset_Password(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String new_password = request.getParameter("new_password");
		String conf_password = request.getParameter("conf_password");
		String email = request.getParameter("email");
		String token = request.getParameter("token");
		map.addAttribute("email", email);
		map.addAttribute("token", token);
		map.addAttribute("resetPwd", "false");
		if(new_password != null && conf_password != null && new_password.equals(conf_password))
		{
			if(Validation.validatePassword(new_password))
			{
				if(email != null && email.length() > 0 && token != null && token.length() > 0 )
				{
					LoginInfo info = loginInfoService.getLoginInfoByUserId(email);
					if(info != null && info.getForgotPwdId().equals(token))
					{
						boolean st = loginInfoService.resetPassword(email, new_password); 
						System.out.println("Password reset status : " + st);
						map.remove("email");
						map.remove("token");
						map.addAttribute("resetPwd", "true");
						return "redirect:login";
						
					}
					map.addAttribute("errorMsg", "Invalid User !");
					return "resetPassword";
				}
				map.addAttribute("errorMsg", "Invalid link !");
				return "resetPassword";
			}
			map.addAttribute("errorMsg", "Password not valid, Please use alphanumeric spring");
			return "resetPassword";
		}
		map.addAttribute("errorMsg", "Password not matched");
		return "resetPassword";
	}
	
}
