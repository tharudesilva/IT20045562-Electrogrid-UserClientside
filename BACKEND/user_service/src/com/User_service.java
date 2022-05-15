package com;
import model.UserService;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


//For JSON
import com.google.gson.*;


//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Users")

public class User_service {
	
	
	UserService userObj = new UserService();
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	//register user
	public String insertUser(
	@FormParam("name") String name,
	@FormParam("address") String address,
	@FormParam("email") String email,
	@FormParam("contact_no") String contact_no,
	@FormParam("username") String username,
	@FormParam("password") String password
	) 
	
	{
	String output = userObj.insertUser(name,address,email,contact_no,username,password);
	return output;
	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUserDetails()//view all users
	{
		return userObj.readUserDetails();
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(String userData)//update user
	{
		//Convert the input string to a JSON object
		 JsonObject userObject = new JsonParser().parse(userData).getAsJsonObject();
		//Read the values from the JSON object
		 String user_id = userObject.get("user_id").getAsString();
		 String name = userObject.get("name").getAsString();
		 String address = userObject.get("address").getAsString();
		 String email = userObject.get("email").getAsString();
		 String contact_no = userObject.get("contact_no").getAsString();
		 String username = userObject.get("username").getAsString();
		 String password = userObject.get("password").getAsString();
		 String output = userObj.EditUserDetails(user_id, name, address, email, contact_no, username, password);
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUser(String userData)//delete users
	{
	//Convert the input string to an XML document
	Document doc = Jsoup.parse(userData, "", Parser.xmlParser());

	//Read the value from the element <userId>
	String userID = doc.select("user_id").text();
	String output = userObj.deleteUser(userID);
	return output;
	}
	@GET
	@Path("/getUserbyID/{userId}")//view a specific user
	@Produces(MediaType.TEXT_HTML)
	public String UserProfileDetails(@PathParam("userId") String userId) {

		return userObj.fetchUser(userId);
	}
	
}

