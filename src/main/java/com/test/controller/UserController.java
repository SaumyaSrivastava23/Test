package com.test.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.test.config.ProjectConfig;
import com.test.domain.NewRecipe;
import com.test.domain.Registration;
import com.test.model.NewRecipeModel;
import com.test.service.RecipeService;
import com.test.service.RegisterService;

@Controller
public class UserController {
	
	
	@Autowired private RegisterService registrationservice;
	@Autowired private RecipeService recipeService;
	
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
	
	

	@RequestMapping(value="/userDashboard", method=RequestMethod.GET)
	public String userDashboard(ModelMap map, HttpServletRequest request, Principal principal)
	{
		List <NewRecipe> recipeList=recipeService.getRecipeList(principal.getName());
		map.addAttribute("recipeList", recipeList);
		System.out.println("user dashboard page of user controller");
		return "userDashboard";
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
			
			System.out.println("In add recipe method");
			Date date = new Date();
			Date dt=new Date(date.getTime());
			recipe.setCreatedDate(dt);
			
			try{
				 Registration reg= registrationservice.getRegistrationByUserId(principal.getName());
				 recipe.setRegistration(reg);
				 String userId=reg.getUserId();
				 
				 MultipartFile recipeImage = model.getRecipeImage();
		    
		            if(recipeImage != null)
		            {
			            String oldImage=recipeImage.getOriginalFilename();
			            
			            String newImage=null;
			            try
			            {
			                if(!(oldImage.equals("")))
			                {
			                	newImage=oldImage.replace(" ", "-");
			                	
			                    recipe.setNewRecipeImage(newImage);
			                    
			                    File img = new File (ProjectConfig.upload_path+"/"+userId+"/Recipe_Image/"+newImage);
			                    
			                    if(!img.exists())
			                    {
			                        img.mkdirs();
			                    }
			                    recipeImage.transferTo(img);  
			                }
			            }
			            catch (IOException ie)
			                    {
			                ie.printStackTrace();
			            }
					}
		            
				 if(recipeImage!=null)
				 {
				 
					 recipeService.addNewRecipe(recipe);
				 }
				
			}
			catch(Exception e){
				
				e.printStackTrace();
				return "addRecipe";
			}
			
			
			return "redirect:userDashboard";
			
		}
	}
	
	
}
