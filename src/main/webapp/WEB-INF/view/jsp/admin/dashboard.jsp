<%@page import="com.test.domain.NewRecipe"%>
<%@page import="com.test.domain.Registration"%>
<%@page import="com.test.config.DateFormats"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<section>
        <div class="container">
        
   <% 
		Registration registration=(Registration)(request.getSession().getAttribute("registration"));
       
   %>       
            <div class="center">  
                 <h2>My Recipes</h2>
            </div> 
             <!--  <div class="box-tools pull-right"> -->
				         <div class="pull-right " style="padding-left: 20px;">
				         	
				         	<a href="addRecipe"><!-- <button class="btn btn-danger btn-xs delete"> --><i class="fa fa-plus"></i>Add Recipe<!-- </button> --></a>
				         	&nbsp;
<!-- 				         	<a href="adminEmpListExport" ><button class="btn btn-primary btn-sm"><i class="fa fa-fw fa-cloud-upload"></i> Export List</button> </a>
 -->				         	
				         </div>
				     <!--   </div> -->
            
   <%--         <h1>
			     <small><%= registration.getName() %></small> 
		        </h1>
   --%>           <div class="row contact-wrap"> 
                       
                  <table class="table">
							<thead>
								<tr>
								    <th style="width: 10%;">S.No.</th>
									<th style="width: 20%;">Recipe Image</th>
									<th style="width: 30%;">Recipe Name</th>
									<th style="width: 10%;">Date</th>
									<th style="width: 10%;" class="text-center">Action</th>
								</tr>
							</thead>
								<tbody>
							<%
								List<NewRecipe> recp_list = (List)request.getAttribute("recipeList");
								if(recp_list != null && !recp_list.isEmpty())
								{ 
									int i=1;
									for(NewRecipe rl : recp_list)
									{
										%>
											<tr id="id">
											    <td style="font-weight: bold;"><%= i++ %></td>
											    <%
													if(rl.getNewRecipeImage() != null)
													{
														String img_path = "/food_uploads/"+registration.getUserId()+"/Recipe_Image/"+rl.getNewRecipeImage();
														%>
															<td title="click to view recipe image"><a href="<%=img_path %>"><img class="direct-chat-img bg-info" alt="" src="<%= img_path%>" style="width: 50px;height: 50px;"></a></td>
														<%
													}
													else
													{
														%>
															<td><img class="direct-chat-img" alt="" src="images/Camera_Icon.png" style="width: 50px;height: 50px;"></td>
														<%
													}
												%>
												<td><%= rl.getRecipeTitle() %></td>
												<td><%= DateFormats.ddMMMyyyy().format(rl.getCreatedDate()) %> </td>
												
											    <td class="text-center">
												<a id=<%= rl.getRecipeId()%> class="btn btn-danger btn-xs delete" href="#"><i class="fa fa-remove"></i>Delete</a>
							                    </td>
											</tr>
										<%
									}
								}
								else
								{
									%>
										<tr>
										  <td colspan="3">No data in the data source.</td>
										</tr>
									<%
								}
							%>
							
							</tbody>
						</table>
        
              </div>
        </div>
</section>
</body>
</html>