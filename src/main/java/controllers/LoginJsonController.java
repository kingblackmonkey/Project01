package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.AsExistingPropertyTypeSerializer;

import exeptions.CustomError;
import exeptions.SessionExpried;
import model.RepayRequest;
import model.User;
import service.RepayRequestService;
import service.UserService;
import success.RequestSuccessful;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class LoginJsonController {

	
	
//	private static UserDao uDao = new UserDaoConcrete();
//	private static UserService uServ = new UserService(uDao);

	public static void login(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{

	
	
		
		
		//To read in stringified JSON data is a bit more complicated,
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = req.getReader();

		String line;
		while((line = reader.readLine()) != null) {
			buffer.append(line);
			buffer.append(System.lineSeparator());
		}
		String data = buffer.toString();
//		System.out.println(data);
	
		ObjectMapper mapper = new ObjectMapper();
//		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		
		//-----------------------------------------------------------------------------------------
		//this is to turn json array of object to  java array list and send back request; field of object user or request from client must be the same in user or request
//		List <User> users = mapper.readValue(data, new TypeReference<List<User>>(){});
//		
//		System.out.println(users);
//		res.setStatus(HttpServletResponse.SC_OK);
//		res.getWriter().write(new ObjectMapper().writeValueAsString(users));

		
		//----------------------------------
		
		//parsing single object
		
		JsonNode parsedObj = mapper.readTree(data);

		String username = parsedObj.get("username").asText();
		String password = parsedObj.get("password").asText();

		try {
			System.out.println("In login json handler   " +  username + "    "   + password );
		
//			User u = uServ.signIn(username, password);
			//System.out.println(u);
			
			//We will keep track of if the user is logged in by storing their id in the session		
			
			
             //		req.getSession().setAttribute("id", u.getId());
		
	

			UserService userService = new UserService(); 
		 User logginedUser = 	userService.signin(username, password);
//		 logginedUser.setRepayRequestList(null);
//	 System.out.println(logginedUser.getUserrole().getId());

	 HttpSession session =  req.getSession();
	
	 session.setAttribute("id",logginedUser.getId());
	 session.setAttribute("userrole",logginedUser.getUserrole().getUsertype());
	 
	 logginedUser = new User( logginedUser.getId(),logginedUser.getUsername()     );
	 
	
//	  System.err.println( session + "       " );
//	 System.out.println( session.getId() );
	 System.err.println(req.getSession().getAttribute("id"));
	 System.err.println( req.getSession().getAttribute("userrole"));
//		res.addHeader("Access-Control-Allow-Origin", "*");
//		res.setHeader("Access-Control-Allow-Methods", "POST");
//res.setHeader("Cookie", "JSESSIONID="+  session.getId() );
		res.setStatus(HttpServletResponse.SC_OK);
		res.getWriter().write(mapper.writeValueAsString(logginedUser));

			
		
			

			

		
			
		}
		catch(Exception e) {
		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		CustomError error = new CustomError();
		error.setErrorMessage(e.getMessage());
		
		res.getWriter().write(new ObjectMapper().writeValueAsString( error));
		
	
		}
		

		

		

		
	}
	
	
	
	
	



public static void getEmployeeInfo(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
	
	
	
	
	//To read in stringified JSON data 
//	StringBuilder buffer = new StringBuilder();
//	BufferedReader reader = req.getReader();
//
//	String line;
//	while((line = reader.readLine()) != null) {
//		buffer.append(line);
//		buffer.append(System.lineSeparator());
//	}
//	String data = buffer.toString();
//	System.out.println(data);

	ObjectMapper mapper = new ObjectMapper();

	
	//-----------------------------------------------------------------------------------------
	//this is to turn json array of object to  java array list and send back request; field of object user or request from client must be the same in user or request
//	List <User> users = mapper.readValue(data, new TypeReference<List<User>>(){});
//	
//	System.out.println(users);
//	res.setStatus(HttpServletResponse.SC_OK);
//	res.getWriter().write(new ObjectMapper().writeValueAsString(users));

	
	//----------------------------------
	

	


	try {
	
	

		
		//We will keep track of if the user is logged in by storing their id in the session		
		

	


		UserService userService = new UserService(); 


		 System.out.println("inside get employee info controller"); 
 HttpSession session =  req.getSession();
 //get the logined user id out; can be employye or manager; but front end only employee can view info; i dont have include case for manager


	 int userId ;
String userrole ; 


System.out.println(session.getAttribute("id")); 
System.out.println(session.getAttribute("userrole")); 



if (  session.getAttribute("id") != null) {
	userId = (Integer) session.getAttribute("id");
	 userrole =  (String) session.getAttribute("userrole"); 
} else {
	throw new SessionExpried();
}





//System.out.println(userId);
//
//System.out.println(userrole);

 User userFound =   userService.getEmployeeInfo(userId );
 System.out.println(userFound);

 //create the new user for json  process;
 
User userFoundForJson = new User();


userFoundForJson.makeUserInfo( userFound.getId() , userFound.getFirstname(), userFound.getLastname(),   userFound.getUsername() , userrole );      
 
 
System.out.println(userFoundForJson.getFirstname());
System.out.println(userFoundForJson.getLastname());
System.out.println(userFoundForJson.getUsername());


// logginedUser = new User( logginedUser.getId(),logginedUser.getUsername()     );
// 
// 
//
	res.setStatus(HttpServletResponse.SC_OK);
	res.getWriter().write(mapper.writeValueAsString(userFoundForJson));

		
	
		

		

	
		
	}
	catch(Exception e) {
		//catch block in client will run because of this status forbidden
	res.setStatus(HttpServletResponse.SC_FORBIDDEN);
	CustomError error = new CustomError();
	error.setErrorMessage(e.getMessage());
	
	res.getWriter().write(new ObjectMapper().writeValueAsString( error));
	

	}
	

	
	
	
	
	}

















//for employee

public static void submitRepayRequest(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
	


	UserService userService = new UserService(); 


	 System.out.println("inside submitRepayRequest controller"); 










try {
	
	HttpSession session =  req.getSession();
	//get the logined user id out; can be employye or manager; but front end only employee can view info; i dont have include case for manager


	 int userId ;
	String userrole ; 

	if (  session.getAttribute("id") != null) {
	userId = (Integer) session.getAttribute("id");
	 userrole =  (String) session.getAttribute("userrole"); 
	} else {
	throw new SessionExpried();
	}


	StringBuilder buffer = new StringBuilder();
	BufferedReader reader = req.getReader();

	String line;
	while((line = reader.readLine()) != null) {
		buffer.append(line);
		buffer.append(System.lineSeparator());
	}
	String data = buffer.toString();

	System.out.println(data);

	ObjectMapper mapper = new ObjectMapper();
	//mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

	//-----------------------------------------------------------------------------------------
	//this is to turn json array of object to  java array list and send back request; field of object user or request from client must be the same in user or request
	//List <User> users = mapper.readValue(data, new TypeReference<List<User>>(){});
	//
	//System.out.println(users);
	//res.setStatus(HttpServletResponse.SC_OK);
	//res.getWriter().write(new ObjectMapper().writeValueAsString(users));

	if (  session.getAttribute("id") != null) {
		userId = (Integer) session.getAttribute("id");
		 userrole =  (String) session.getAttribute("userrole"); 
	} else {
		throw new SessionExpried();
	}



	//parsing single object

	JsonNode parsedObj = mapper.readTree(data);

	int amount =   parsedObj.get("amount").asInt();
	String category = parsedObj.get("category").asText();

	System.out.println("amount " + amount);

	System.out.println("category " + category);
	
	
	
	
	
	userService.submitRepayRequest(userId,amount, category);
	
	
	res.setStatus(HttpServletResponse.SC_OK);
	
	RequestSuccessful requestSucessful = new RequestSuccessful();
	res.getWriter().write(new ObjectMapper().writeValueAsString(requestSucessful ));
	
} catch (Exception e) {
	// TODO: handle exception
	
	res.setStatus(HttpServletResponse.SC_FORBIDDEN);
	CustomError error = new CustomError();
	error.setErrorMessage(e.getMessage());
	
	res.getWriter().write(new ObjectMapper().writeValueAsString( error));
	
}

  
	
	
}




//for employee


public static void viewAllMyPendingRepayRequests(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{


	
	RepayRequestService repayRequestService = new RepayRequestService();
	
	
	
	try {
		
		HttpSession session =  req.getSession();
		//get the logined user id out; can be employye or manager; but front end only employee can view info; i dont have include case for manager


		 int userId ;
	

		if (  session.getAttribute("id") != null) {
		userId = (Integer) session.getAttribute("id");
	
		} else {
		throw new SessionExpried();
		}



//		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

		//-----------------------------------------------------------------------------------------
		//this is to turn json array of object to  java array list and send back request; field of object user or request from client must be the same in user or request
		//List <User> users = mapper.readValue(data, new TypeReference<List<User>>(){});
		//
		//System.out.println(users);
		//res.setStatus(HttpServletResponse.SC_OK);
		//res.getWriter().write(new ObjectMapper().writeValueAsString(users));




	
		
		
		

		
		
	res.setStatus(HttpServletResponse.SC_OK);
//		
//	
		res.getWriter().write(new ObjectMapper().writeValueAsString( 	 repayRequestService.viewMyPendingRequest(userId)   ));
		
	} catch (Exception e) {
		// TODO: handle exception
		
		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		CustomError error = new CustomError();
		error.setErrorMessage(e.getMessage());
		
		res.getWriter().write(new ObjectMapper().writeValueAsString( error));
		
	}


}

	public static void viewAllMyApproveAndDeniedRepayRequests(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{


		
		RepayRequestService repayRequestService = new RepayRequestService();
		
		
		
		try {
			
			HttpSession session =  req.getSession();
			//get the logined user id out; can be employye or manager; but front end only employee can view info; i dont have include case for manager


			 int userId ;
		

			if (  session.getAttribute("id") != null) {
			userId = (Integer) session.getAttribute("id");
		
			} else {
			throw new SessionExpried();
			}





			
			
			res.setStatus(HttpServletResponse.SC_OK);

			res.getWriter().write(new ObjectMapper().writeValueAsString( 	 repayRequestService.viewAllMyApprovedAndDeniedRequest(userId)   ));
			
		} catch (Exception e) {
			// TODO: handle exception
			
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			CustomError error = new CustomError();
			error.setErrorMessage(e.getMessage());
			
			res.getWriter().write(new ObjectMapper().writeValueAsString( error));
			
		}




}
	
	
	
	
	
	
	
	
	public static void updateMyInfo(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		
		
try {
			
	UserService userService = new UserService(); 
	
			HttpSession session =  req.getSession();
			//get the logined user id out; can be employye or manager; but front end only employee can view info; i dont have include case for manager


			 int userId ;
		

			if (  session.getAttribute("id") != null) {
			userId = (Integer) session.getAttribute("id");
		
			} else {
			throw new SessionExpried();
			}



//			ObjectMapper mapper = new ObjectMapper();
			//mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

			//-----------------------------------------------------------------------------------------
			//this is to turn json array of object to  java array list and send back request; field of object user or request from client must be the same in user or request
			//List <User> users = mapper.readValue(data, new TypeReference<List<User>>(){});
			//
			//System.out.println(users);
			//res.setStatus(HttpServletResponse.SC_OK);
			//res.getWriter().write(new ObjectMapper().writeValueAsString(users));



			//To read in stringified JSON data is a bit more complicated,
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();

			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			String data = buffer.toString();
			System.out.println(data);
		
			ObjectMapper mapper = new ObjectMapper();

			
			JsonNode parsedObj = mapper.readTree(data);

			String newusername = parsedObj.get("newusername").asText();
			String newpassword = parsedObj.get("newpassword").asText();
		
			
			
			
			 userService.updateMyInfo(userId,  newusername, newpassword);
			
			
			res.setStatus(HttpServletResponse.SC_OK);
			RequestSuccessful requestSuccessful = new RequestSuccessful();
			res.getWriter().write(new ObjectMapper().writeValueAsString( 	 requestSuccessful   ));
			
		} catch (Exception e) {
			// TODO: handle exception
			
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			CustomError error = new CustomError();
			error.setErrorMessage(e.getMessage());
			
			res.getWriter().write(new ObjectMapper().writeValueAsString( error));
			
		}
	
	
	}	
	
	
	
	

	
	//for manager
	public static void approveRepayRequest(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		
		try {
			
			
			HttpSession session =  req.getSession();
			


		
		
//if not manger or manger not logined throw session expired 
			if (  session.getAttribute("id") == null || ! session.getAttribute("userrole").equals("manager")) {
			System.err.println(" inside the if exprired session");
			System.err.println(  session.getAttribute("id") == null  );		
			System.err.println( !session.getAttribute("userrole").equals("manager")) ;
				throw new SessionExpried();
		
			} 

			
			
			
			
			RepayRequestService repayRequestService = new RepayRequestService();
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			
			ObjectMapper mapper = new ObjectMapper();

			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			String data = buffer.toString();
			System.out.println(data);
			
//			List <Int> users = mapper.readValue(data, new TypeReference<List<User>>(){});
			
			ArrayList<Integer> repayRequestIds =mapper.readValue(data, new TypeReference<ArrayList<Integer>>(){});
		
			   for (int i = 0; i < repayRequestIds.size();i++) { 		      
			                 		
			          repayRequestService.approveRepayRequest(    repayRequestIds.get(i)         );  
			   
			   }   
				
			
			
				res.setStatus(HttpServletResponse.SC_OK);
				RequestSuccessful requestSuccessful = new RequestSuccessful();
				res.getWriter().write(new ObjectMapper().writeValueAsString( 	 requestSuccessful   ));
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			CustomError error = new CustomError();
			error.setErrorMessage(e.getMessage());
			
			res.getWriter().write(new ObjectMapper().writeValueAsString( error));
			
			
		}
		
		
		

	
	
	
	
	}
	
	
	

	
	
	
	
	
	
	
	
	//for manager
	
public static void denyRepayRequest(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		
		
		try {
			
			
			HttpSession session =  req.getSession();
			


		
		
//if not manger or manger not logined throw session expired 
			if (  session.getAttribute("id") == null || ! session.getAttribute("userrole").equals("manager")) {
			System.err.println(" inside the if exprired session");
//			System.err.println(  session.getAttribute("id") == null  );		
//			System.err.println( !session.getAttribute("userrole").equals("manager")) ;
				throw new SessionExpried();
		
			} 

			
			
			
			
			RepayRequestService repayRequestService = new RepayRequestService();
			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();
			
			ObjectMapper mapper = new ObjectMapper();

			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			String data = buffer.toString();
			System.out.println(data);
			
//			List <Int> users = mapper.readValue(data, new TypeReference<List<User>>(){});
			
			ArrayList<Integer> repayRequestIds =mapper.readValue(data, new TypeReference<ArrayList<Integer>>(){});
		
			   for (int i = 0; i < repayRequestIds.size();i++) { 		      
			                 		
			          repayRequestService.denyRepayRequest(    repayRequestIds.get(i)         );  
			   
			   }   
				
			
			
				res.setStatus(HttpServletResponse.SC_OK);
				RequestSuccessful requestSuccessful = new RequestSuccessful();
				res.getWriter().write(new ObjectMapper().writeValueAsString( 	 requestSuccessful   ));
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			
			res.setStatus(HttpServletResponse.SC_FORBIDDEN);
			CustomError error = new CustomError();
			error.setErrorMessage(e.getMessage());
			
			res.getWriter().write(new ObjectMapper().writeValueAsString( error));
			
			
		}
		
	
	
	
	
	
}
	
	
	
	
	
	
	
	//for manager
public static void viewAllPendingOfAllEmployees(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
	
	
	
	RepayRequestService repayRequestService = new RepayRequestService();
	
	
	
	try {
		
		HttpSession session =  req.getSession();	


	

			if (  session.getAttribute("id") == null || ! session.getAttribute("userrole").equals("manager")) {
				System.err.println(" inside the if exprired session");

					throw new SessionExpried();
			
				} 	
		

		
		
	res.setStatus(HttpServletResponse.SC_OK);

		res.getWriter().write(new ObjectMapper().writeValueAsString( repayRequestService.viewPendingRequestOfAllEmployees()	   ));
		
	} catch (Exception e) {
		// TODO: handle exception
		
		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		CustomError error = new CustomError();
		error.setErrorMessage(e.getMessage());
		
		res.getWriter().write(new ObjectMapper().writeValueAsString( error));
		
	}
	
	
	
}
	
	
	
	
	//for manager
public static void viewAllApprovedDeniedOfAllEmployees(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
	
	
	
	RepayRequestService repayRequestService = new RepayRequestService();
	
	
	
	try {
		
		HttpSession session =  req.getSession();	


	

			if (  session.getAttribute("id") == null || ! session.getAttribute("userrole").equals("manager")) {
				System.err.println(" inside the if exprired session");

					throw new SessionExpried();
			
				} 	
		

		
		
	res.setStatus(HttpServletResponse.SC_OK);

		res.getWriter().write(new ObjectMapper().writeValueAsString( repayRequestService.viewAllApprovedDeniedOfAllEmployees()	   ));
		
	} catch (Exception e) {
		// TODO: handle exception
		
		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		CustomError error = new CustomError();
		error.setErrorMessage(e.getMessage());
		
		res.getWriter().write(new ObjectMapper().writeValueAsString( error));
		
	}
	
	
	
	
	
	
	
	
}
	
	
	



//for manager
public static void viewAllRepayRequestOfAanEmployee(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
	
	
	
	RepayRequestService repayRequestService = new RepayRequestService();
	
	
	
	try {
		
		
		
		
		
		
		
		
		HttpSession session =  req.getSession();	


	

			if (  session.getAttribute("id") == null || ! session.getAttribute("userrole").equals("manager")) {
				System.err.println(" inside the if exprired session");

					throw new SessionExpried();
			
				} 	
		

			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = req.getReader();

			String line;
			while((line = reader.readLine()) != null) {
				buffer.append(line);
				buffer.append(System.lineSeparator());
			}
			String data = buffer.toString();
			System.out.println(data);
		
			ObjectMapper mapper = new ObjectMapper();

			
			//parsing single object
			
			JsonNode parsedObj = mapper.readTree(data);

			int employeeId = parsedObj.get("id").asInt();
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		
	res.setStatus(HttpServletResponse.SC_OK);

		res.getWriter().write(new ObjectMapper().writeValueAsString( repayRequestService.viewAllRepayRequestOfAnEmployee( employeeId)	   ));
		
	} catch (Exception e) {
		// TODO: handle exception
		
		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		CustomError error = new CustomError();
		error.setErrorMessage(e.getMessage());
		
		res.getWriter().write(new ObjectMapper().writeValueAsString( error));
		
	}
	
	
	
	
	
	
	
	
}
	










//for manager; //manager get all employess info; not  including manager

public static void viewAllEmployeesInfo(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
	
	
	
	
	

	UserService userService = new UserService(); 

	
	

	
	
	try {
		
		
		
		
		HttpSession session =  req.getSession();	


		

		if (  session.getAttribute("id") == null || ! session.getAttribute("userrole").equals("manager")) {
			System.err.println(" inside the if exprired session");

				throw new SessionExpried();
		
			} 	

	List<User> usersListArrList = 	userService.getAllAllEmployeesInfo();
		
	List <User>  usersListArrListForJson = new ArrayList<User>();


		for (int i = 0; i <  usersListArrList.size(); i++) {
			User employeeForJson = new User();
			
			employeeForJson.makeUserInfo(  usersListArrList.get(i).getId(), usersListArrList.get(i).getFirstname(), usersListArrList.get(i).getLastname(),    usersListArrList.get(i).getUsername() ,"employee" );  
			
			 usersListArrListForJson.add(employeeForJson);
		}
			
	 
		
		
		
		res.setStatus(HttpServletResponse.SC_OK);

		res.getWriter().write(new ObjectMapper().writeValueAsString(   usersListArrListForJson     ));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	} catch (Exception e) {
		// TODO: handle exception
	
	

		res.setStatus(HttpServletResponse.SC_FORBIDDEN);
		CustomError error = new CustomError();
		error.setErrorMessage(e.getMessage());
		
		res.getWriter().write(new ObjectMapper().writeValueAsString( error));
	
	
	
	
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
}











public static void getSession(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{

	HttpSession session =  req.getSession();	
	System.out.println("inside the get function");
	System.out.println(   session.getAttribute("id")           );
	
	
	
	
	

}


public static void logout(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{

	req.getSession().invalidate();
	
	 res.getWriter().println("Logged Out");
	
}

}