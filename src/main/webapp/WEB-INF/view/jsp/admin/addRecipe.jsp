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
<section>

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

                </form:form>
  <%
  
	} else {
		
		%>
	
		<%
		
		
		out.println("Firstly login to add recipe..");

	}
%>
</div>


</section>



</body>
</html>