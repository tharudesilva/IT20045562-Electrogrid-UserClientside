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
<title>Delete User Details</title>
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
		<a id="li a" href="UpdateUser.jsp" style="font-family:sans-serif;">
                     Update User Details
        </a>
	</div >
	<div class="right" id="li">
		<a id="li a" class="active" href="#" style="font-family:sans-serif;">
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
                    Delete User Details
        </a>
    </div>
</div>
<!-- Page Navigation -->
<!-- Page Body -->
<br><br><br>
<div class="page-wrapper">
	<div class="container2">
		<div style="overflow-x: auto;" id="center">
		<%
		//Delete user Data----------------------------------
		if (request.getParameter("user_id") != null)
		{
		UserService userObj = new UserService();
		String stsMsg = userObj.deleteUser(request.getParameter("user_id"));
		session.setAttribute("statusMsgDelete", stsMsg);
		}   
	
		UserService userObj = new UserService();
		out.print(userObj.showdeleteUserbtn());
		%>
		</div>
	</div>
</div>
<hr>
<div id="alertError" class="alert alert-danger">
<%
	 out.print(session.getAttribute("statusMsgDelete"));
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