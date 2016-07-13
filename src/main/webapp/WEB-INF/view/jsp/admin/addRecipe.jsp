<%@ page import="com.test.domain.Registration" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title></title>
</head>
<body>
<section style="background-color: cream;">
        <div class="container">
            

   <% 
		Registration registration=(Registration)(request.getSession().getAttribute("registration"));
   %>
           <div class="center">  
            <br>      
                <h2>Add New Recipe</h2>
            </div> 
              <div class="row contact-wrap"> 
            
	            <%
	            if(registration!=null){
	            
	            %>
            
                <div class="status alert alert-success" style="display: none"></div>
                    <form:form role="form" class="contact-form" id="form_add" commandName="recForm" method="post" action="addRecipe" onsubmit="return validateForm()">
                         
                         <div class="form-group">
                            <label>Recipe Title</label><span class="text-danger">*</span>
                             <form:input path="recipeTitle" class="form-control" maxlength="150" tabindex="1" placeholder="Enter Recipe Title" autofocus="autofocus" />
	                         <span><form:errors path="recipeTitle" /></span>
	                     </div>
                   
                        <div class="form-group">
                            <label>Recipe Description</label><span class="text-danger">*</span>
       		                <form:textarea path="recipeDetail" class="form-control" tabindex="5" rows="8" placeholder="Enter Recipe Detail" />
		                    <span><form:errors path="recipeDetail" /></span>
		                 </div>                        
                        <div class="form-group">
                            <button type="submit" name="submit" class="btn btn-primary btn-lg btnSubmit" required="required">Submit</button>
                        </div>
  
                </form:form>
			 
			  <%
			  
				} else {
					
			  %>
				<div class="center">
			        
			        <p>The Page you are looking for that Firstly you have to login.....</p>
			        <a class="btn btn-primary" href="login">GO BACK FOR LOGIN</a>
			    </div>
			    
				<%
					}
			    %>
        </div>
	</div>
</section>



</body>
</html>