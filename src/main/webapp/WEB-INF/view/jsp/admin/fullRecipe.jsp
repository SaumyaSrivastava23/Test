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


<section id="blog" class="container">

   <% 
        Registration registration=(Registration)(request.getSession().getAttribute("registration"));
		NewRecipe newRecipe=(NewRecipe)(request.getAttribute("recipeDetail"));
       
   %>
    <%
   if(newRecipe!=null){


   %>
        <div class="center">
            <h2><%=newRecipe.getRecipeTitle() %></h2>
            <p class="lead">Pellentesque habitant morbi tristique senectus et netus et malesuada</p>
        </div>
  
        <div class="blog">
            <div class="row">
                <div class="col-md-8">
                    <div class="blog-item">
                    <div class="input-group pull-right">
    					<a href="editRecipe?recipeId=<%=newRecipe.getRecipeId()%>"><i class="fa fa-pencil"></i></a>
	                 </div>
                       <%
								if(newRecipe.getNewRecipeImage() != null)
								{
									String img_path = "/food_uploads/"+registration.getUserId()+"/Recipe_Image/"+newRecipe.getNewRecipeImage();
									%>
										<a href="<%=img_path %>"><img class="img-responsive img-blog" alt="" src="<%= img_path%>" style="width: 50%; height: 50%;" title="click to view recipe image"></a>
									<%
								}
								else
								{
									%>
										<img class="img-responsive img-blog" alt="" src="images/Camera_Icon.png" style="width: 50px;height: 50px;">
									<%
								}
							%>
                            <div class="row">  
                                <div class="col-xs-12 col-sm-2 text-center">
                                    <div class="entry-meta">
                                        <span id="publish_date"><%=DateFormats.ddMMM().format(newRecipe.getCreatedDate()) %></span>
                                        <span><i class="fa fa-user"></i> <a href="#"> <%=registration.getName() %></a></span>
                                        <span><i class="fa fa-comment"></i> <a href="#">2 Comments</a></span>
                                        <span><i class="fa fa-heart"></i><a href="#">56 Likes</a></span>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-10 blog-content">
                                    <h2><%=newRecipe.getRecipeTitle() %></h2>
                                    <h3>Ingredients</h3>
                                    <p><%=newRecipe.getIngredients() %></p>

                                    <h3>Detail</h3>
                                    <p><%=newRecipe.getRecipeDetail() %></p>

                                    <div class="post-tags">
                                        <strong>Tag:</strong> <a href="#">Cool</a> / <a href="#">Creative</a> / <a href="#">Dubttstep</a>
                                    </div>

                                </div>
                            </div>
                        </div><!--/.blog-item-->
                        
                        <div class="media reply_section">
                            <div class="pull-left post_reply text-center">
                                <a href="#"><img src="images/blog/boy.png" class="img-circle" alt="" /></a>
                                <ul>
                                    <li><a href="#"><i class="fa fa-facebook"></i></a></li>
                                    <li><a href="#"><i class="fa fa-twitter"></i></a></li>
                                    <li><a href="#"><i class="fa fa-google-plus"></i> </a></li>
                                </ul>
                            </div>
                            <div class="media-body post_reply_content">
                                <h3>Antone L. Huges</h3>
                                <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariaturp</p>
                                <p><strong>Web:</strong> <a href="http://www.shapebootstrap.net">www.shapebootstrap.net</a></p>
                            </div>
                        </div> 
                        
                        <h1 id="comments_title">5 Comments</h1>
                        <div class="media comment_section">
                            <div class="pull-left post_comments">
                                <a href="#"><img src="images/blog/girl.png" class="img-circle" alt="" /></a>
                            </div>
                            <div class="media-body post_reply_comments">
                                <h3>Marsh</h3>
                                <h4>NOVEMBER 9, 2013 AT 9:15 PM</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud</p>
                                <a href="#">Reply</a>
                            </div>
                        </div> 
                        <div class="media comment_section">
                            <div class="pull-left post_comments">
                                <a href="#"><img src="images/blog/boy2.png" class="img-circle" alt="" /></a>
                            </div>
                            <div class="media-body post_reply_comments">
                                <h3>Marsh</h3>
                                <h4>NOVEMBER 9, 2013 AT 9:15 PM</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud</p>
                                <a href="#">Reply</a>
                            </div>
                        </div> 
                        <div class="media comment_section">
                            <div class="pull-left post_comments">
                                <a href="#"><img src="images/blog/boy3.png" class="img-circle" alt="" /></a>
                            </div>
                            <div class="media-body post_reply_comments">
                                <h3>Marsh</h3>
                                <h4>NOVEMBER 9, 2013 AT 9:15 PM</h4>
                                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud</p>
                                <a href="#">Reply</a>
                            </div>
                        </div> 

                    </div><!--/.col-md-8-->

                   	 <aside class="col-md-4">			
    				<div class="widget categories">
                        <h3>Recent Comments</h3>
                        <div class="row">
                            <div class="col-sm-12">
                                <div class="single_comments">
    								<img src="images/blog/avatar3.png" alt=""  />
    								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do </p>
                                    
    							</div>
    							<div class="single_comments">
    								<img src="images/blog/avatar3.png" alt=""  />
    								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do </p>
    								
    							</div>
    							<div class="single_comments">
    								<img src="images/blog/avatar3.png" alt=""  />
    								<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do </p>
    								
    							</div>
    							
                            </div>
                        </div>                     
                    </div><!--/.recent comments-->
                     
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

         </div><!--/.blog-->
         
         
         <%
   } 
    else
   {
   	%>
   		<div class="blog">
            <div class="row">
                <div class="col-md-8">
                    <div class="blog-item">
					     
						<h2>No Data available</h2>
					</div>	
				</div>
			</div>
	    </div>
   	<%
   }
			%>
         
       

    </section><!--/#blog-->








</body>
</html>