package com;
import java.sql.*;
public class User
{
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "sokian@981119");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public String readUsers()
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
	output = "<table class='table' border='1'><thead class='table-success'><tr><th scope='col'>User Full Name</th><th scope='col'>User Address</th>" +
			 "<th scope='col'>Email</th>" +"<th scope='col'>Contact Number</th>" +"<th scope='col'>Username</th>" +
			 "<th scope='col'>Password</th>" +"<th scope='col'>Update</th>"+"<th scope='col'>Delete</th></tr></thead><tbody>";
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
		output += "<tr><td class='table-warning'><input id='hidUserIDUpdate' name='hidUserIDUpdate' type='hidden' value='" + user_id
		+ "'>" + name + "</td>";
		output += "<td class='table-warning'>" + address + "</td>";
		output += "<td class='table-warning'>" + email + "</td>";
		output += "<td class='table-warning'>" + contact_no + "</td>";
		output += "<td class='table-warning'>" + username + "</td>";
		output += "<td class='table-warning'>" + password + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-itemid='"+ user_id + "'>" + "</td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-itemid='"+ user_id + "'>" + "</td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the items.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	

	public String insertUser(String name, String address, String email, String contact_no, String username, String password )
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into user (`user_id`,`name`,`address`,`email`,`contact_no`,`username`,`password`)"
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
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +
					newUsers + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateUser(String user_id,String name,String address,String email, String contact_no, String username, String password)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{
				return "Error while connecting to the database for updating.";
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

			//execute the statement
			preparedStmt.execute();
			con.close();
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +
					newUsers + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while updating the user.\"}";
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
			String newUsers = readUsers();
			output = "{\"status\":\"success\", \"data\": \"" +
					newUsers + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the user.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
