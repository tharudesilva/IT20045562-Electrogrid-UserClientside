package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserService {
	 //A common method to connect to the DB
		private Connection connect()
		 {
		 Connection con = null;
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver");

		 //Provide the correct details: DBServer/DBName, username, password
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "sokian@981119");
		 }
		 catch (Exception e)
		 {e.printStackTrace();}
		 return con;
		 }
		
		//insert data into database
		public String insertUser(String name, String address, String email, String contact_no, String username, String password)
		 {
		 String output = "";
		 try
		 {
		 Connection con = connect();
		 if (con == null)
		 {return "Error while connecting to the database for inserting."; }
		 // create a prepared statement
		 String query = " insert into user(`user_id`,`name`,`address`,`email`,`contact_no`,`username`,`password`)"
		 + " values (?, ?, ?, ?, ?, ?, ?)";
		 PreparedStatement preparedStmt = con.prepareStatement(query);
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(2, name);
		 preparedStmt.setString(3, address);
		 preparedStmt.setString(4, email);
		 preparedStmt.setString(5, contact_no);
		 preparedStmt.setString(6, username);
		 preparedStmt.setString(7, password);
		 // execute the statement
		 
		 preparedStmt.execute();
		 con.close();
		 output = "Inserted successfully";
		 }
		 catch (Exception e)
		 {
		 output = "Error while inserting the item.";
		 System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		//read date in databse
		public String readUserDetails()
		
		{
			 String output = "";
			try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for reading.";
			 }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th>User ID</th><th>User Full Name</th>"
			 +"<th>User Address</th><th>Email</th>"
			 + "<th>Contact Number</th>"
			 + "<th>Username</th>"
			 + "<th>Password</th>";
			 String query = "select * from user";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
				 String user_id = Integer.toString(rs.getInt("user_id"));
				 String name = rs.getString("name");
				 String address = rs.getString("address");
				 String email = rs.getString("email");
				 String contact_no = rs.getString("contact_no");
				 String username = rs.getString("username");
				 String password = rs.getString("password");
				 // Add a row into the html table
				 output += "<tr><td>" + user_id + "</td>";
				 output += "<td>" + name + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + email + "</td>";
				 output += "<td>" + contact_no + "</td>"; 
				 output += "<td>" + username + "</td>";
				 output += "<td>" + password + "</td>";
				 
				 
				 // buttons
				 output += "<td><form method='post' action='UpdateUser.jsp'>"
						 + "<input name='btnUpdate' type='submit' value='Update' class='btn btn-secondary'>"
						 + "<input name='user_id' type='hidden' value='" + user_id + "'>" + "</form></td>"
					+ "<td><form method='post' action='ViewUser.jsp'>"
					+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
					+"<input name='user_id' type='hidden' value='" + user_id + "'>"+ "</form></td></tr>";
			 }
			 con.close();
			 
			 // Complete the html table
			 output += "</table>";
			 }
			catch (Exception e)
			 {
				 output = "Error while reading the user details"
				 		+ ".";
				 System.err.println(e.getMessage());
			 }
			return output;
		}
		
		
		//Show user data section
		public String showUserDataselectbtn()
		{
				String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for reading."; }
					 
					 // Prepare the html table to be displayed
					 
					 output = "<table class='table' border='1'><thead class='table-success'><tr><th scope='col'>User ID</th><th scope='col'>User Name</th><th scope='col'>User Address</th>" +
							 "<th scope='col'>Email</th>" +"<th scope='col'>Contact Number</th>" +"<th scope='col'>Username</th>" +
							 "<th scope='col'>Password</th>" +"<th scope='col'>Action</th></tr></thead><tbody>";
				
					 String query = "select * from user";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 // iterate through the rows in the result set
					 
					 while (rs.next())
					 {
						 String user_id = Integer.toString(rs.getInt("user_id"));
						 String name = rs.getString("name");
						 String address = rs.getString("address");
						 String email = rs.getString("email");
						 String contact_no = rs.getString("contact_no");
						 String username = rs.getString("username");
						 String password = rs.getString("password");
						 
						
						 
						 // Add into the html table
						 
						 output += "<tr><td class='table-warning'>" + user_id +  "</td>"; 
						 output += "<td class='table-warning'>" + name + "</td>";
						 output += "<td class='table-warning'>" + address + "</td>";
						 output += "<td class='table-warning'>" + email + "</td>";
						 output += "<td class='table-warning'>" + contact_no + "</td>";
						 output += "<td class='table-warning'>" + username + "</td>";
						 output += "<td class='table-warning'>" + password + "</td>";
						 
						 
						 
						 // buttons
						 
						 output += "<td><form method='put' action='UpdateUser.jsp'>"
								 + "<input name='btnUpdate' type='submit' value='Select User Profile' class='btn btn-secondary'>"
								 + "<input name='recordID' type='hidden' value='" + user_id + "'>"  + "</form></td></tr></tbody>";	
					 }
					 con.close();
					 
					 // Complete the html table
					 
					 output += "</table>";
				 }
				 catch (Exception e)
				 {
					 output = "Error while reading the User";
					 System.err.println(e.getMessage());
				 }
				 return output;
		}
		
		
		//Delete view User data section
		public String showdeleteUserbtn()
		{
				String output = "";
				 try
				 {
					 Connection con = connect();
					 if (con == null)
					 {return "Error while connecting to the database for reading."; }
					 
					 // Prepare the html table to be displayed
					 
					 output = "<table class='table' border='1'><thead class='table-success'><tr><th scope='col'>User ID</th><th scope='col'>User Full Name</th><th scope='col'>User Address</th>" +
							 "<th scope='col'>Email</th>" +"<th scope='col'>Contact Number </th>" +"<th scope='col'>Username</th>" +"<th scope='col'>Password</th>" + 
						     "<th scope='col'>Remove User Profile</th></tr></thead><tbody>";
				
					 String query = "select * from user";
					 Statement stmt = con.createStatement();
					 ResultSet rs = stmt.executeQuery(query);
					 
					 // iterate through the rows in the result set
					 
					 while (rs.next())
					 {
						 String user_id = Integer.toString(rs.getInt("user_id"));
						 String name = rs.getString("name");
						 String address = rs.getString("address");
						 String email = rs.getString("email");
						 String contact_no = rs.getString("contact_no");
						 String username = rs.getString("username");
						 String password = rs.getString("password");
						 
						
						 
						 // Add into the html table
						 
						 output += "<tr><td class='table-warning'>" + user_id +  "</td>"; 
						 output += "<td class='table-warning'>" + name + "</td>";
						 output += "<td class='table-warning'>" + address + "</td>";
						 output += "<td class='table-warning'>" + email + "</td>";
						 output += "<td class='table-warning'>" + contact_no + "</td>";
						 output += "<td class='table-warning'>" + username + "</td>";
						 output += "<td class='table-warning'>" + password + "</td>";
						 
						 
						 
						 // buttons
						 
						 output += "<td><form method='delete' action='DeleteUser.jsp'>"
									+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
									+ "<input name='user_id' type='hidden' value='" + user_id + "'>" + "</form></td></tr></tbody>";		 
					 }
					 con.close();
					 
					 // Complete the html table
					 
					 output += "</table>";
				 }
				 catch (Exception e)
				 {
					 output = "Error while reading the user details";
					 System.err.println(e.getMessage());
				 }
				 return output;
		}
		
		
		
		public String EditUserDetails(String user_id,String name,String address,String email, String contact_no, String username, String password)
		   {
			   String output = "";
			   try
				   {
				   Connection con = connect();
				   if (con == null)
				   {
					   return "Error while connecting to the database for updating"; 
				   }
				   // create a prepared statement
				   String query = "UPDATE user SET name=?,address=?,email=?,contact_no=?,username=?,password=?WHERE user_id=?";
				   PreparedStatement preparedStmt = con.prepareStatement(query);
				   // binding values
				   preparedStmt.setString(1, name);
				   preparedStmt.setString(2, address);
				   preparedStmt.setString(3, email);
				   preparedStmt.setString(4, contact_no);
				   preparedStmt.setString(5, username);
				   preparedStmt.setString(6, password);
				   preparedStmt.setInt(7, Integer.parseInt(user_id));
				   // execute the statement
				   preparedStmt.execute();
				   con.close();
				   output = "Updated successfully";
				   }
			    catch (Exception e)
				{
				   output = "Error while updating the user";
				   System.err.println(e.getMessage());
				}
			    return output;
			    }
		
		
		public String deleteUser(String user_id)
		{
		String output = "";
		try
		{
		Connection con = connect();
		if (con == null)
		{
		return "Error while connecting to the database for deleting.";
		}

		// create a prepared statement
		String query = "delete from user where user_id=?";
		PreparedStatement preparedStmt = con.prepareStatement(query);

		// binding values
		preparedStmt.setInt(1, Integer.parseInt(user_id));

		// execute the statement
		preparedStmt.execute();
		con.close();
		output = "User account Deleted successfully";
		}
		catch (Exception e)
		{
		output = e.toString();
		//System.err.println(e.getMessage());
		}
		return output;
		}
		
		public String fetchUser(String user_id)
		
		{
			 String output = "";
			try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for reading";
			 }
			 // Prepare the html table to be displayed
			 output = "<table border='1'><tr><th> User Full Name</th>"
			 +"<th>User Address</th><th>Email</th>"
			 + "<th>Contact Number</th>"
			 + "<th>Username</th>"
			 + "<th>Password</th></tr>";
			 String query = "select * from user where user_id='"+user_id+"'";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 // iterate through the rows in the result set
			 while (rs.next())
			 { 
				 String userid = Integer.toString(rs.getInt("user_id"));
				 String name = rs.getString("name");
				 String address = rs.getString("address");
				 String email = rs.getString("email");
				 String contact_no = rs.getString("contact_no");
				 String username = rs.getString("username");
				 String password = rs.getString("password");
				 // Add a row into the html table
				 
				 output += "<tr><td>" + name + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + email + "</td>"; 
				 output += "<td>" + contact_no + "</td>";
				 output += "<td>" + username + "</td>";
				 output += "<td>" + password + "</td></tr>";
				 // buttons
				 output += "<input name='itemID' type='hidden' "
				 + " value='" + user_id + "'>"
				 + "</form></td></tr>";
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			 
			 }
			catch (Exception e)
			 {
//				 output = "Error while reading the user details";
				output=e.toString();
				 System.err.println(e.getMessage());
			 }
			return output;
		}
		
		
}
