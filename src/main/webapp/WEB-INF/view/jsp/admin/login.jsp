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
            <div class="row"> 
                <div class="status alert alert-success" style="display: none"></div>
                <form action="j_spring_security_check" method="post" id="login_form">
	              <div class="col-sm-4"></div>
                    <div class="col-sm-4">
                    <center>
                          <c:if test="${param.error}">
							<div class="error" style="height: auto;">
								Your login attempt was not successful, try again.<br /> Reason:
								${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
							</div>
							<br>
						  </c:if>
						<%
							String resetPwd = (String)request.getParameter("resetPwd");
							if(resetPwd != null && resetPwd.equals("true"))
							{
								%>
									<div class="text-green" style="height: auto;">
										Password reset successfully, Login with new password ! <br>
									</div>
									<br>
								<%
							}
						%>
    
  			             <div class="panel panel-default">
							<div class="panel-heading">
							  <h1 class="panel-title">
								<a class="accordion-toggle" href="#collapseTwo1" data-parent="#accordion1" data-toggle="collapse">
			     				  Sign In
							    </a>
							  </h1>
						    </div>
						 </div>	           
				     </center>       
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Email" name="j_username" id="j_username" required="required">
                            <span class="input-group-addon"><i class="fa fa-envelope-o"></i></span>
                        </div>
                        <br>
                        <div class="input-group">
                            <input type="password" class="form-control" placeholder="Password" name="j_password" id="j_password" required="required">
                            <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                        </div>
                           <br>
                        <div class="row">
					        <div class="col-xs-8">
					          <div class="checkbox">
					            <label>
					              <input type="checkbox" name="_spring_security_remember_me"> Remember Me
					            </label>
					          </div>
					        </div>
					        <!-- /.col -->
					        <div class="col-xs-4">
					          <button type="submit" class="btn btn-primary btn-block btn-flat"><i class="fa fa-fw fa-sign-in"></i> Sign In</button>
					        </div>
					        <!-- /.col -->
					    </div>
					    <a href="forgotPassword">I forgot my password</a><br>
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
	$("#login_form").trigger('reset');
});


</script>
</html>