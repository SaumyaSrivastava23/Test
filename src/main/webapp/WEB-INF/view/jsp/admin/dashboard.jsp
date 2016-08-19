<%@page import="com.test.domain.NewRecipes"%>
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


<section id="blog" class="container">

<% 
		Registration registration=(Registration)(request.getSession().getAttribute("registration"));
       
   %>


        <div class="center">
            <h2>My Recipes</h2>
            <p class="lead">Pellentesque habitant morbi tristique senectus et netus et malesuada</p>
        </div>

        <div class="blog">
            <div class="row">
                 <div class="col-md-8">
                  <%
								List<NewRecipes> recp_list = (List)request.getAttribute("recipeList");
								if(recp_list != null && !recp_list.isEmpty())
								{ 
									int i=1;
									for(NewRecipes rl : recp_list)
									{
										%>
                    <div class="blog-item">
                        <div class="row">
                            <div class="col-xs-12 col-sm-2 text-center">
                                <div class="entry-meta">
                                    <span id="publish_date"><%=DateFormats.ddMMM().format(rl.getCreatedDate()) %></span>
                                    <span><i class="fa fa-user"></i> <a href="#"><%=registration.getName() %></a></span>
                                    <span><i class="fa fa-comment"></i> <a href="blog-item.html#comments">2 Comments</a></span>
                                    <span><i class="fa fa-heart"></i><a href="#">56 Likes</a></span>
                                </div>
                            </div>
                            
                            <div class="input-group pull-right">
    					        <a href="editRecipe?recipeId=<%=rl.getRecipeId()%>"><i class="fa fa-pencil"></i></a>
	                 		</div>
	                 		
                            <div class="col-xs-12 col-sm-10 blog-content">
                           
                             <%
													if(rl.getNewRecipeImage() != null)
													{
														String img_path = "/food_uploads/"+registration.getUserId()+"/Recipe_Image/"+rl.getNewRecipeImage();
														%>
															<a href="<%=img_path %>"><img class="img-responsive img-blog" alt="" src="<%= img_path%>" style="width: 100px;height: 100px;" title="click to view recipe image"></a>
														<%
													}
													else
													{
														%>
															<img class="img-responsive img-blog" alt="" src="images/Camera_Icon.png" style="width: 50px;height: 50px;">
														<%
													}
												%>
                                                               
                                
                                <h2><a href="fullRecipe?recipeId=<%=rl.getRecipeId()%>"><%=rl.getRecipeTitle() %></a></h2>
                                <h3><%=rl.getRecipeDetail() %></h3>
                                <a class="btn btn-primary readmore" href="fullRecipe?recipeId=<%=rl.getRecipeId()%>">Read More <i class="fa fa-angle-right"></i></a>
                            </div>
                        
                        </div>    
                    </div><!--/.blog-item-->
                    <% 
									}
								}
                            else
								{
									%>
										<h3>No data in the data source.</h3>
										
									<%
								}
							%>       
                    <ul class="pagination pagination-lg">
                        <li><a href="#"><i class="fa fa-long-arrow-left"></i>Previous Page</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">Next Page<i class="fa fa-long-arrow-right"></i></a></li>
                    </ul><!--/.pagination-->
                </div><!--/.col-md-8-->

                <aside class="col-md-4">
                    <div class="widget search">
                        <form role="form">
                                <input type="text" class="form-control search_box" autocomplete="off" placeholder="Search Here">
                        </form>
                    </div><!--/.search-->
    				
    				<div class="widget categories">
                        <h3>Recent Comments</h3>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="single_comments">
                                    <img src="images/blog/avatar3.png" alt=""  />
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do </p>
                                    <div class="entry-meta small muted">
                                        <!-- <span>By <a href="#">Alex</a></span <span>On <a href="#">Creative</a></span> -->
                                    </div>
                                </div>
                                <div class="single_comments">
                                    <img src="images/blog/avatar3.png" alt=""  />
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do </p>
                                    <div class="entry-meta small muted">
                                       <!--  <span>By <a href="#">Alex</a></span <span>On <a href="#">Creative</a></span> -->
                                    </div>
                                </div>
                                <div class="single_comments">
                                    <img src="images/blog/avatar3.png" alt=""  />
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do </p>
                                    <div class="entry-meta small muted">
                                        <!-- <span>By <a href="#">Alex</a></span <span>On <a href="#">Creative</a></span> -->
                                    </div>
                                </div>
                                
                            </div>
                        </div>                     
                    </div><!--/.recent comments-->
                     

                    <div class="widget categories">
                        <h3>Categories</h3>
                        <div class="row">
                            <div class="col-sm-6">
                                <ul class="blog_category">
                                    <li><a href="#">Computers <span class="badge">04</span></a></li>
                                    <li><a href="#">Smartphone <span class="badge">10</span></a></li>
                                    <li><a href="#">Gedgets <span class="badge">06</span></a></li>
                                    <li><a href="#">Technology <span class="badge">25</span></a></li>
                                </ul>
                            </div>
                        </div>                     
                    </div><!--/.categories-->
    				
    				<div class="widget archieve">
                        <h3>Archieve</h3>
                        <div class="row">
                            <div class="col-sm-12">
                                <ul class="blog_archieve">
                                    <li><a href="#"><i class="fa fa-angle-double-right"></i> December 2013 <span class="pull-right">(97)</span></a></li>
                                    <li><a href="#"><i class="fa fa-angle-double-right"></i> November 2013 <span class="pull-right">(32)</a></li>
                                    <li><a href="#"><i class="fa fa-angle-double-right"></i> October 2013 <span class="pull-right">(19)</a></li>
                                    <li><a href="#"><i class="fa fa-angle-double-right"></i> September 2013 <span class="pull-right">(08)</a></li>
                                </ul>
                            </div>
                        </div>                     
                    </div><!--/.archieve-->
    				
                    <div class="widget tags">
                        <h3>Tag Cloud</h3>
                        <ul class="tag-cloud">
                            <li><a class="btn btn-xs btn-primary" href="#">Apple</a></li>
                            <li><a class="btn btn-xs btn-primary" href="#">Barcelona</a></li>
                            <li><a class="btn btn-xs btn-primary" href="#">Office</a></li>
                            <li><a class="btn btn-xs btn-primary" href="#">Ipod</a></li>
                            <li><a class="btn btn-xs btn-primary" href="#">Stock</a></li>
                            <li><a class="btn btn-xs btn-primary" href="#">Race</a></li>
                            <li><a class="btn btn-xs btn-primary" href="#">London</a></li>
                            <li><a class="btn btn-xs btn-primary" href="#">Football</a></li>
                            <li><a class="btn btn-xs btn-primary" href="#">Porche</a></li>
                            <li><a class="btn btn-xs btn-primary" href="#">Gadgets</a></li>
                        </ul>
                    </div><!--/.tags-->
    				
    				<div class="widget blog_gallery">
                        <h3>Our Gallery</h3>
                        <ul class="sidebar-gallery">
                            <li><a href="#"><img src="images/blog/gallery1.png" alt="" /></a></li>
                            <li><a href="#"><img src="images/blog/gallery2.png" alt="" /></a></li>
                            <li><a href="#"><img src="images/blog/gallery3.png" alt="" /></a></li>
                            <li><a href="#"><img src="images/blog/gallery4.png" alt="" /></a></li>
                            <li><a href="#"><img src="images/blog/gallery5.png" alt="" /></a></li>
                            <li><a href="#"><img src="images/blog/gallery6.png" alt="" /></a></li>
                        </ul>
                    </div><!--/.blog_gallery-->
    			</aside>  
            </div><!--/.row-->
        </div>
    </section><!--/#blog-->














<%-- <section>
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
            
           <h1>
			     <small><%= registration.getName() %></small> 
		        </h1>
             <div class="row contact-wrap"> 
                       
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
</section> --%>
</body>
</html>