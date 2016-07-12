<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>

</head>
<body>


<section id="contact-page">
        <div class="container">
            <div class="center">
            <br>        
                <h2>Drop Your Message</h2>
            </div> 
            <div class="row contact-wrap"> 
                <div class="status alert alert-success" style="display: none"></div>
                <form:form role="form" class="contact-form" id="form_con" commandName="cuForm" method="post" action="contact" onsubmit="return validateForm()">
                    <div class="col-sm-5 col-sm-offset-1">
                        <div class="form-group">
                            <label>Name</label><span class="text-danger">*</span>
                             <form:input path="name" class="form-control" maxlength="50" tabindex="1" placeholder="Enter Name" autofocus="autofocus" />
	                         <span><form:errors path="name" /></span>
	                     </div>
                        <div class="form-group">
                            <label>Email</label><span class="text-danger">*</span>
				             <form:input path="emailId" class="form-control" maxlength="60" tabindex="5"  placeholder="Enter Email Id" />
							 <span><form:errors path="emailId" /></span>
				        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <form:input path="contactNo" class="form-control" maxlength="11" tabindex="10" placeholder="Enter Number" />
	                        <span><form:errors path="contactNo" /></span>
	                    </div>
                                            
                    </div>
                    <div class="col-sm-5">
                        <div class="form-group">
                            <label>Subject</label><span class="text-danger">*</span>
         		             <form:input path="subject" class="form-control" maxlength="60" tabindex="15"  placeholder="Enter subject" />
		                     <span><form:errors path="subject" /></span>
		                </div>
                        <div class="form-group">
                            <label>Message</label><span class="text-danger">*</span>
       		                <form:textarea path="message" class="form-control" tabindex="20" rows="4" placeholder="Enter message" />
		                    <span><form:errors path="message" /></span>
		                 </div>                        
                        <div class="form-group">
                            <button type="submit" name="submit" class="btn btn-primary btn-lg btnSubmit" required="required">Submit Message</button>
                        </div>
                    </div>
               </form:form>
            </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
    
<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.full.js"></script>

</body>

<script type="text/javascript">



$(document).ready(function()
		{
			$("#form_con").trigger('reset');
		});


	function validateForm()
	{
		
		var name = $("#form_con #name").val();
		var emailId = $("#form_con #emailId").val();
		var subject = $("#form_con #subject").val();
		var message = $("#form_con #message").val();
		
  		var valid = true;
		$('.has-error').removeClass("has-error");
						
		if(name == "")
		{
			$("#name").parent().addClass("has-error")
			valid = false;
		}
		
		if(emailId == "" || !isEmail(emailId))
		{
			$("#emailId").parent().addClass("has-error")
			valid = false;
		}
		
		if(subject == "")
		{
			$("#subject").parent().addClass("has-error")
			valid = false;
		}
		
		if(message == "")
		{
			$("#message").parent().addClass("has-error")
			valid = false;
		}
		
				
		if(!valid)
		{
			return false;		
		}
		$(".btnSubmit").attr("disabled","disabled");
		$(".btnSubmit").text("Submiting...");
	}

</script>

</html>