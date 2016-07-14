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


<section>
        <div class="container">
        <div class="box box-info">
            <div class="center">  
                
                <h2>Register Here</h2>
            </div> 
            <div class="row contact-wrap"> 
               <div class="status alert alert-success" style="display: none"></div>
                <!-- <div class="col-sm-12 col-sm-offset-1"> -->
                  <form:form role="form" method="POST" action="addUser" commandName="regForm" name="regForm" autocomplete="off" enctype="multipart/form-data" id="form_reg" onsubmit="return validateForm()">
			           
                      <div class="col-sm-5 col-sm-offset-1">
                         <div class="form-group">
                            <label>Name</label><span class="text-danger">*</span>
                              <form:input path="name" class="form-control" maxlength="50" tabindex="1" placeholder="Enter Name" autofocus="autofocus" />
	                     </div>
	                     <div class="form-group">
                            <label>Password</label><span class="text-danger">*</span>
                               <form:password path="password" class="form-control" maxlength="20" tabindex="10" placeholder="Enter Password" />
	                     </div>
                         <div class="form-group">
                            <label>Contact Number</label><span class="text-danger">*</span>
                              <form:input path="contactNumber" class="form-control" maxlength="11" tabindex="20" placeholder="Enter Number" />
	                     </div>
                                                  
                         <div class="col-lg-6 no padding">
		                  <label>Gender</label><span class="text-danger">*</span>
		                  <div class="input-group">
		                        <span class="input-group-addon">
		                          <form:radiobutton path="gender" value="male" tabindex="15" />
		                        </span>
		                    <label class="form-control">Male</label>
		                  </div>
		                
		                </div>
		                <div class="col-lg-6">
		                  <label >&nbsp;</label>
		                  <div class="input-group">
		                        <span class="input-group-addon">
		                          <form:radiobutton path="gender" value="female" tabindex="20"/>
		                        </span>
		                    <label class="form-control">Female</label>
		                  </div>
		                </div> 
                  
                                          
	                  </div>
                      <div class="col-sm-5">
 				        <div class="form-group">
                            <label>Email</label><span class="text-danger">*</span>
                              <form:input path="userId" class="form-control" maxlength="60" tabindex="5"  placeholder="Enter Email Id" />
			                  <span class="text-danger emailid_error"><form:errors path="userId" /></span>
                        </div>
                         
                        <div class="form-group">
                            <label>Confirm Password</label><span class="text-danger">*</span>
                              <form:password path="confirmPassword" class="form-control" maxlength="20" tabindex="15" placeholder="Re-Enter Password" />
	                    </div>
	                    
				        <div class="form-group">
                            <label>Date of Birth</label><span class="text-danger">*</span>
                              <form:input path="dob" class="form-control dob" placeholder="dd-MM-yyyy" tabindex="25" />
	                    </div>
				        
                               
                        <div class="form-group">
                            <label>Address</label>
                             <form:input path="address" class="form-control" maxlength="100" tabindex="35" placeholder="Enter Address" />
	                    </div>                        
                      
                       
				              
			            <div class="form-group">
	                          <button type="submit" name="submit" class="btn btn-primary btn-lg btnSubmit">Submit</button>
	                    </div> 
                             
				      </div>
                         
               </form:form>
              </div> 
             </div><!--/.row-->
        </div><!--/.container-->
    </section><!--/#contact-page-->
    
   
<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.full.js"></script>
<script type="text/javascript">


$(document).ready(function()
{
	$("#form_reg").trigger('reset');
});


$('#dob').datetimepicker({
	timepicker:false,
	maxDate:new Date(),
	format:'d-m-Y'
});



$(document).ready(function()
{
	
	  $(document.body).on("change", "#userId", function() {
		 $(".emailid_error").html("");
		 var userId = $(this).val();
		 $.ajax({
 			type : "GET",
 			 url : "validateUserId",
 			data : {'userId':userId},
 			contentType : "application/json",
 			success : function(data) {
 				var obj = jQuery.parseJSON(data);
 				if(obj.UserId_exist)
				{
					$(".emailid_error").html("User Id already exist !");
					$("#userId").focus();
				}
 			}
 		});
	 });
});

</script>


<script type="text/javascript">
	function validateForm()
	{
		
		var name = $("#form_reg #name").val();
		var userId = $("#form_reg #userId").val();
		var password = $("#form_reg #password").val();
		var confirmPassword = $("#form_reg #confirmPassword").val();
		var dob = $("#form_reg #dob").val();
	    var radioValue = $("input[name='gender']:checked").val();
        var address = $("#form_reg #address").val();
        var contactNumber = $("#form_reg #contactNumber").val();
       
        
        
		var valid = true;
		$('.has-error').removeClass("has-error");
		
				
		if(name == "")
		{
			$("#name").parent().addClass("has-error")
			valid = false;
		}
		
		if(userId == ""|| !isEmail(userId))
		{
			$("#form_reg #userId").parent().addClass("has-error")
			valid = false;
		}
		
		if(password == "" || !checkComplexity(password))
		{
			$("#password").parent().addClass("has-error")
			valid = false;
		}
		
		if(confirmPassword == "" || password != confirmPassword|| !checkComplexity(repassword))
		{
			$("#confirmPassword").parent().addClass("has-error")
			valid = false;
		}
				
		if(dob == "")
		{
			$("#dob").parent().addClass("has-error")
			valid = false;
			
		}
				
		if(radioValue == undefined)
		{
			$("input[name='gender']").parent().parent().addClass("has-error");
			valid = false;
		}
		
		if(address == "")
		{
			$("#address").parent().addClass("has-error")
			valid = false;
		}
		
		if(contactNumber == "")
		{
			$("#contactNumber").parent().addClass("has-error")
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



</body>
</html>