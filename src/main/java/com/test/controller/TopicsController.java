package com.test.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TopicsController {

	
	
	
	@RequestMapping(value="/aboutUs", method=RequestMethod.GET)
	public String aboutUs(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("about us page from User Controller");
		return "aboutUs";
	}
	
	@RequestMapping(value="/services", method=RequestMethod.GET)
	public String services(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("services page from User Controller");
		return "services";
	}
	
	@RequestMapping(value="/team", method=RequestMethod.GET)
	public String team(ModelMap map, HttpServletRequest request, Principal principal)
	{
		System.out.println("team page from User Controller");
		return "team";
	}
	
	
}
