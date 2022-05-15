<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="model.UserService" %>
<!DOCTYPE html>
<html>
<head>
<!-- Page Header Styles -->
<style>
        .pageHead {
            display: flex;
        }

        .left {
            flex: 25%;
            padding: 15px 0;
        }

        .center {
            flex: 50%;
            padding: 15px 0;
        }
        .right {
            flex: 25%;
            padding: 15px 0;
        }   
</style>
<!-- Page Header Styles -->
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/main.js"></script>
<!-- charset meta tag -->
<meta charset="utf-8">
<!-- head code meta tag -->
<meta name="viewport" content="eidth=device-width, initial-scale=1">
<title>Update User DEtails</title>
<!-- CSS Links -->
<link rel ="stylesheet" type="text/css" href="Views/Styles.css">
<link rel ="stylesheet" type="text/css" href="Views/electrogrid.css">
<!-- CSS Links -->
</head>
<body>
<!-- Page Header -->
<div class="pageHead">
	<div class="center" align="center">
		<h1 style="font-family:Brush Script MT;"><span>Electro</span>Grid</h1>
		<h2 style="font-family:Courier New;">Your Power Our Service</h2>
	</div >
</div>
<!-- Page Header -->
<!-- Page Navigation -->
<div class="pageNav" id="ul">

	<div class="left" id="li">
		<a id="li a" href="ViewUser.jsp" style="font-family:sans-serif;">
                    View User Details
        </a>
	</div>
	<div class="center1" id="li">
		<a id="li a" href="InsertUser.jsp" style="font-family:sans-serif;">
                    Register User Details
        </a>
	</div >
	<div class="center2" id="li">
		<a id="li a" class="active" href="#" style="font-family:sans-serif;">
                     Update User Details
        </a>
	</div >
	<div class="right" id="li">
		<a id="li a" href="DeleteUser.jsp" style="font-family:sans-serif;">
                    Delete User Details
        </a>
	</div>
</div>
<div class="innerpageNav" id="ul2">
	<div class="left1" id="li2">
	<a href="#">
		<font  style="font-family:Brush Script MT ; text-align:center;"><span>Electro</span>Grid</font>
		</a>
    </div>
	<div  style="text-align:center;" class="right1" id="li2">
		<a id="li2 a" style="font-family:sans-serif;">
                    Update User Details
        </a>
    </div>
</div>
<!-- Page Navigation -->
<!-- Page Body -->
<% 
	    
	//Update Powerusage Data----------------------------------
	if (request.getParameter("user_id") != null)
	 {
	 UserService userObj = new UserService();
	 String stsMsg = userObj.EditUserDetails(request.getParameter("user_id"),
	
	 request.getParameter("name"),
	 request.getParameter("address"),
	 request.getParameter("email"),
	 request.getParameter("contact_no"),
	 request.getParameter("username"),
	 request.getParameter("password"));
	 session.setAttribute("statusMsgUpdate", stsMsg);
	 }
	%>
	
	<%
			String user_id = request.getParameter("user_id");
			String name = request.getParameter("name");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String contact_no = request.getParameter("contact_no");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
		%>
	
	
	
	
	
<div class="page-wrapper">
	<div class="container">
					<form  method="post" action="UpdateUser.jsp">
					<div class="row">
						<div class="col-25" id="center">
						 	User ID:
						</div>
						<div class="col-75" style="width: 25%;" class="form-control form-control-sm">
							<input  name="recordID" type="text" value="<%= user_id %>">
						</div>
					 </div>
					 <br>
					<div class="row">
						<div class="col-25" id="center">
						 	User Full Name:
						</div>
						<div class="col-75" style="width: 25%;" class="form-control form-control-sm">
						 	<input  name="name" type="text" >
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-25" id="center"> 
							User Address:
						</div>
						<div class="col-75" style="width: 25%;" class="form-control form-control-sm">
							<input  name="address" type="text">
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-25" id="center"> 
							Email:
						</div>
						<div class="col-75" style="width: 25%;" class="form-control form-control-sm">
							<input  name="email" type="text">
						</div>
					</div>
					<br> 
					<div class="row">
						<div class="col-25" id="center"> 
							Contact Number:
						</div>
						<div class="col-75" style="width: 25%;" class="form-control form-control-sm">
							<input  name="contact_no" type="text">
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-25" id="center">  
							Username:
						</div>
						<div class="col-75" style="width: 25%;" class="form-control form-control-sm">
							<input  name="username" type="text">
						</div>
					</div>
					<br> 
					
					<div class="row">
						<div class="col-25" id="center">  
							Password:
						</div>
						<div class="col-75" style="width: 25%;" class="form-control form-control-sm">
							<input  name="password" type="text">
						</div>
					</div>
					<br>
					<div class="pageNav" >
						<div class="left" id="center">
							<input name="btnsubmit" type="submit" value="Update" class="btn btn-primary" onclick="myFunction()">
						</div>
					</div>
					</form>
	</div>
	<div class="container2">
		<div style="overflow-x: auto;" id="center">
			<%
			UserService userObj = new UserService();
			out.print(userObj.showUserDataselectbtn());
			%>
		</div>	
	</div>
</div>
<hr>
<div id="alertSuccess" class="alert alert-success">
<%
	 out.print(session.getAttribute("statusMsgUpdate"));
	%>
</div>
<hr>
<!-- Footer -->
<div class="footer">
	
	<div class="footer-content">
		  
		<div class="footer-section-about">
			
			<h1 style="font-family:Brush Script MT;"><span>Electro</span>Grid</h1>
			
			<p>We Provide Your Power nedds.
			</p>
			
			<h3> Our Location </h3>
			<font size="3">No 220.<br> Flower Rd, <br> Colombo 07 <br> E-Mail :- ElectroGridSL@Gmail.com 
			
			</font>
		</div>	
		
		<div class="footer-section-links">
		
		</div>
		
		<div class="footer-section-contact-form">
			<h2>Contact Us</h2>
			
			<h3> Hotline : 011-275-497-2</h3>
		
			<div class="socials">
				<a href="#"><i class="fab fa-facebook"></i></a>
				<a href="#"><i class="fab fa-instagram"></i></a>
				<a href="#"><i class="fab fa-twitter"></i></a>
				<a href="#"><i class="fab fa-youtube"></i></a>
			</div>
			
		</div>
	</div>
	

	<div class="footer-bottom">
		
		&copy; ElectroGrid.com | Designed by HIRU Designers
	</div>

</div>
<!-- /Footer-->
</body>
</html>