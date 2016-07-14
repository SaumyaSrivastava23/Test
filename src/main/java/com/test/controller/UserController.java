package com.test.controller;

import java.security.Principal;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.test.domain.NewRecipe;
import com.test.domain.Registration;
import com.test.model.NewRecipeModel;
import com.test.service.RecipeService;
import com.test.service.RegisterService;

@Controller
public class UserController {
	
	
	@Autowired private RegisterService registrationservice;
	@Autowired private RecipeService recipeService;
	
	@RequestMapping(value="/userDashboard", method=RequestMethod.GET)
	public String userDashboard(ModelMap map, HttpServletRequest request, Principal principal)
	{
		Registration reg=registrationservice.getRegistrationByUserId(principal.getName());
		map.addAttribute("reg", reg);
		System.out.println("user dashboard page of user controller");
		return "userDashboard";
	}

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
	
	@RequestMapping(value="/addRecipe", method=RequestMethod.GET)
	public String addRecipe(ModelMap map, HttpServletRequest request, Principal principal)
	{
		map.addAttribute("recForm", new NewRecipeModel());
		System.out.println("addRecipe page from User Controller");
		return "addRecipe";
	}
	
	@RequestMapping(value="/addRecipe", method=RequestMethod.POST)
	public String addRecipes(@ModelAttribute(value="recForm") @Valid NewRecipeModel model, BindingResult result,
		                  @ModelAttribute(value="recipe") NewRecipe recipe, ModelMap map, HttpServletRequest request, Principal principal)
	{
		if(result.hasErrors()){
			
			System.out.println("Invalidate ");
			return "addRecipe";
		}
		else{
			Date date = new Date();
			Date dt=new Date(date.getTime());
			recipe.setCreatedDate(dt);
			
			try{
				 Registration reg= registrationservice.getRegistrationByUserId(principal.getName());
				 recipe.setRegistration(reg);
				 recipeService.addNewRecipe(recipe);
				
			}
			catch(Exception e){
				
				e.printStackTrace();
				return "addRecipe";
			}
			
			
			return "addRecipe";
			
		}
	}
	
	
}
