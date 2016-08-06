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
import com.test.domain.Category;
import com.test.domain.NewRecipe;
import com.test.domain.Registration;
import com.test.model.NewRecipeModel;
import com.test.service.CategoryService;
import com.test.service.RecipeService;
import com.test.service.RegisterService;

@Controller
public class UserController {
	
	
	@Autowired private RegisterService registrationservice;
	@Autowired private RecipeService recipeService;
	@Autowired private CategoryService categoryService;
	

	@RequestMapping(value="/userRecipes", method=RequestMethod.GET)
	public String userDashboard(ModelMap map, HttpServletRequest request, Principal principal)
	{
		List <NewRecipe> recipeList=recipeService.getRecipeList(principal.getName());
		map.addAttribute("recipeList", recipeList);
		System.out.println("user dashboard page of user controller");
		return "userRecipes";
	}

	
	@RequestMapping(value="/addRecipe", method=RequestMethod.GET)
	public String addRecipe(ModelMap map, HttpServletRequest request, Principal principal)
	{
		map.addAttribute("recForm", new NewRecipeModel());
		map.addAttribute("cList", categoryService.getCategoryList());
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
				 Category category=categoryService.getCategory(recipe.getCategory().getCategoryId());
				 recipe.setRegistration(reg);
				 recipe.setCategory(category);
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
			
			
			return "redirect:userRecipes";
			
		}
	}
	
	
	@RequestMapping(value = "/editRecipe", method = RequestMethod.GET)
	public String editRecipe(ModelMap map, HttpServletRequest request, Principal principal)
	{
		String recipeId = request.getParameter("recipeId");
		if(recipeId != null && recipeId.length() > 0)
		{
			
			NewRecipe recipe= recipeService.getRecipeId(Integer.parseInt(recipeId));
			if(recipe != null)
			{
				NewRecipeModel model = new NewRecipeModel();
				model.setRecipeId(recipe.getRecipeId());
				model.setRecipeTitle(recipe.getRecipeTitle());
				model.setIngredients(recipe.getIngredients());
				model.setRecipeDetail(recipe.getRecipeDetail());
				model.setCategory(recipe.getCategory());
				map.addAttribute("cList", categoryService.getCategoryList());
				map.addAttribute("recForm", model);
				map.addAttribute("recipeDetail", recipe);
				
				return "editRecipe";
			}
			
		}
		
		return "redirect:userRecipes";
	}
	
	
	
	
	@RequestMapping(value = "/editRecipe", method = RequestMethod.POST)
	public String editRecipes(@ModelAttribute(value = "recipe") @Valid NewRecipeModel model,BindingResult result,
			                ModelMap map, HttpServletRequest request, Principal principal)
	{
		if (result.hasErrors())
		{
			
			System.out.println("in validation");
			return "editRecipe";
		} else
		{
		
			String recipeId = (String)request.getParameter("recipeId");
		  
		    NewRecipe recipe= recipeService.getRecipeId(Integer.parseInt(recipeId));
		    if(recipe!=null)
		    {
			
				boolean flag=false;
				Date date = new Date();
				Date dt = new Date(date.getTime());
				recipe.setModifiedDate(dt);
				
				
				recipe.setRecipeTitle(model.getRecipeTitle());
				recipe.setIngredients(model.getIngredients());
				recipe.setRecipeDetail(model.getRecipeDetail());
				Category category=categoryService.getCategory(model.getCategory().getCategoryId());
				recipe.setCategory(category);
				
				try{
					 Registration reg= registrationservice.getRegistrationByUserId(principal.getName());
					// recipe.setRegistration(reg);
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
			            
				
				flag = recipeService.updateNewRecipe(recipe);
				
				if(flag==true){
					map.addAttribute("status", "success");
				}
				else{
					map.addAttribute("status", "error");
				}
				
				System.out.println("update"+flag);
				}
				catch(Exception e){
					
					e.printStackTrace();
					return "editRecipe";
				}
		}
		return "redirect:userRecipes";
	}
	
}
	
	@RequestMapping(value="/fullRecipe", method=RequestMethod.GET)
	public String fullRecipe(ModelMap map, HttpServletRequest request, Principal principal)
	{
		
		String recipeId=request.getParameter("recipeId");
		NewRecipe recipeDetail= recipeService.getRecipeId(Integer.parseInt(recipeId));
		map.addAttribute("recipeDetail", recipeDetail);
		System.out.println("full recipe page of user controller");
		return "fullRecipe";
		
	}
	
	
}
