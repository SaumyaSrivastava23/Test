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
                <form action="forgotPassword" method="post">
	              <div class="col-sm-4"></div>
                    <div class="col-sm-4">
                    <center>
                          <%
								String resetPwd = (String) request.getAttribute("resetPwd");
								if(resetPwd != null )
								{
									if(resetPwd.equals("true"))
									{
										%>
											<p class="text-info">${resetMsg }</p>
										<%
									}
									else if(resetPwd.equals("false"))
									{
										%>
											<p class="text-danger">${resetMsg }</p>
										<%
									}
									
								}
							%>
                             <br>
  			             <div class="panel panel-default">
							<div class="panel-heading">
							  <h1 class="panel-title">
								<a class="accordion-toggle" href="#collapseTwo1" data-parent="#accordion1" data-toggle="collapse">
			     				  Forget Password
							    </a>
							  </h1>
						    </div>
						 </div>	<br>           
				     </center>       
                        <div class="input-group">
                           <input type="email" class="form-control" placeholder="Email" name="userId" value="${userId}" required="required">
                            <span class="input-group-addon"><i class="fa fa-envelope-o"></i></span>
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
</body>
</html>