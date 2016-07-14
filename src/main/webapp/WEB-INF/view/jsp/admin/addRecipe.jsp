<%@ page import="com.test.domain.Registration" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<title></title>
<!-- <link rel="stylesheet" href="css/bootstrap3-wysihtml5.min.css"> -->

<!-- <style type="text/css">

input[type="file"] {
    display: none !important;
}
.custom-file-upload {
    cursor: pointer;
    margin-top: -30px !important;
    position: absolute !important;
}

</style>
 -->
</head>
<body>
<section>
        <div class="container">
    <% 
		Registration registration=(Registration)(request.getSession().getAttribute("registration"));
   %>
           <div class="center">  
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
                            <label>Recipe Ingredients</label><span class="text-danger">*</span>
                             <form:input path="ingredients" class="form-control" maxlength="150" tabindex="5" placeholder="Enter Recipe Ingredients" autofocus="autofocus" />
	                         <span><form:errors path="ingredients" /></span>
	                     </div>
                   
                        <div class="form-group">
                            <label>Recipe Description</label><span class="text-danger">*</span>
       		                <form:textarea path="recipeDetail" class="form-control ckeditor" tabindex="10" rows="3" cols="50" placeholder="Enter Recipe Detail" />
		                    <span><form:errors path="recipeDetail" /></span>
		                 </div>    
		                 
		                 
		                 
				                <div class="form-group col-xs-12 no-padding">
									<img alt="Recipe" id="output" src="images/Camera_Icon.png" style="max-height: 150px; max-width: 100px" />
								</div>
								<div class="form-group col-xs-12 no-padding">
									<!-- <label class="custom-file-upload btn btn-block"> -->
										<input name="userImage" id="u1" type="file" accept="image/jpg,image/png,image/jpeg,image/gif" tabindex="15" onchange="return ValidateFileUpload()" />
										<!-- <i class="fa fa-fw fa-cloud-upload"></i>
										Browse Image  -->
									<!-- </label> -->
									<span class="text-danger"><form:label path="" id="userImgErr" class="image_error" /></span>
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

<!-- <script src="js/bootstrap3-wysihtml5.all.min.js"></script> -->
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>


</body>
</html>