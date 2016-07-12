<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">

.error {
	color: red;
}
	
</style>
   

</head>
<body>
 <section id="contact-page">
    
     
        <div class="container">
         <br><br>
            <div class="row contact-wrap"> 
                <div class="status alert alert-success" style="display: none"></div>
                <form action="resetPassword" method="post" id="resetPwd" onsubmit="return validate()">
	              <div class="col-sm-4"></div>
                    <div class="col-sm-4">
                    <center>
                           <%
								String resetPwd = (String)request.getAttribute("resetPwd");
								if(resetPwd != null && resetPwd.equals("false"))
								{
									%>
										<p class="text-danger">${errorMsg}</p>
									<%
								}
						   %>
    
  			             <div class="panel panel-default">
							<div class="panel-heading">
							  <h1 class="panel-title">
								<a class="accordion-toggle" href="#collapseTwo1" data-parent="#accordion1" data-toggle="collapse">
			     				  Reset Password
							    </a>
							  </h1>
						    </div>
						 </div>	           
				     </center>       
                       <div class="input-group">
                         <input type="password" class="form-control" placeholder="New password" name="new_password" id="new_password" required="required">
				         <input type="hidden" name="email" value="${email}">
				         <input type="hidden" name="token" value="${token}">
				         <span class="input-group-addon"><i class="fa fa-lock"></i></span>
			           </div>
                        <br>
                      <div class="input-group">
				        <input type="password" class="form-control" placeholder="Confirm password" name="conf_password" id="conf_password" required="required">
				        <span class="input-group-addon"><i class="fa fa-lock"></i></span>
				        <span class="text-danger error error_re"></span>
				      </div>
                           <br>
                        <div class="row">
					        <div class="col-xs-4">
					          <button type="submit" class="btn btn-primary btn-lg" style="text-align: center;">Reset Password</button>
					        </div>
					        <!-- /.col -->
					   </div>               
                   </div>
                   <div class="col-sm-4"></div>    
                </form> 
                
            </div><!--/.row-->
     <div class="row">
       <br><br><br><br>
     </div>
   </div><!--/.container-->
  </section><!--/#contact-page-->
    
 <script src="js/jquery.js"></script>
</body>


<script type="text/javascript">
$(document).ready(function()
{
	$("#resetPwd").trigger('reset');
});

function validate()
{
	var new_password = $("#new_password").val();
	var conf_password = $("#conf_password").val();
	var valid = true;
	if(!checkComplexity(new_password))
	{
		$('.error_pw').text("Invalid Password !")
		valid = false;
	}
	if(new_password != conf_password)
	{
		$('.error_re').text("Password not matched !")
		valid = false;
	}
	if(!valid)
	{
		return false;
	}
	return true;
}
function checkComplexity(password)
{
	var strongRegex = new RegExp("^(?=.{4,})(((?=.*[a-z])(?=.*[0-9]))).*$", "g");
	return strongRegex.test(password)
}
</script>

</html>