package service;

import java.util.List;

import com.example.logging.Logging;

import dao.RepayRequestDao;
import dao.UserDao;
import model.RepayRequest;
import model.User;
import model.UserType;
import exeptions.InvalidCredentials;
import exeptions.RequestFailed;
import exeptions.SubmitRepayRequestFailed;
import exeptions.UserNotFound;
public class UserService {

	
	UserDao userDao = new UserDao();
	
	
	//employee sign in
	
	public User signin (String username, String password) throws  InvalidCredentials{
		
		User logineduser  =    userDao.getAuserByUsernameAndPassword(username,password ) ;
		if ( logineduser != null) {
			return logineduser;
			
		}else {
			
			Logging.logger.warn("User tried to login with invalid credentials");
			Logging.logger.info("User tried to login with invalid credentials");
			throw new InvalidCredentials();
			
		}
		
		
	}
	
	
	
	
	//submit repay request ; employee; sett pending tatus for repay request here
	
	//request dao will return the id of newly created request
	
	//use the newly id to get the request and give back to client

	//after submit repay  request successful; this method will return in for the created repay request 
	
public int  submitRepayRequest (int id, int amount, String category) throws SubmitRepayRequestFailed{
		
	
//UserType usertypeEmployee = new UserType(20, "employee");
//User user = new User(26, "Sun",  "Tran",   "SunTran2800", "789", usertypeEmployee);	
//RepayRequest repayRequest = new RepayRequest( 1000, "pending","other", user);
//RepayRequestDao repayRequestDao = new RepayRequestDao();
	
	
	//get user first 
UserDao userDao = new UserDao();

User user = userDao.selectUser(id);
System.out.println(user.getUserrole());


RepayRequest repayRequest = new RepayRequest( amount, "pending",category, user);
//RepayRequest repayRequest = new RepayRequest(amount, "pending",category, user);

RepayRequestDao repayRequestDao = new RepayRequestDao();
      int createdRepayRequestId =       repayRequestDao.insert(repayRequest);
      
      
      if(   	createdRepayRequestId > 0	  ) {
      
    	  	return  createdRepayRequestId;
      

		
	}else {
		
		throw  new  SubmitRepayRequestFailed();
		
	}
			
			
		
	
	
	
}
	
	
	
	
	
	//get user info; used for employee only in this case
	
	public User getEmployeeInfo (int id) throws  UserNotFound{
		
		//if user is loggin the ssssion should have id in it and should bigger than 0
		//if no id found in seesion throw no user found exeption
		if ( id > 0) {
			
			
			return userDao.selectUser(id);
			
		}else {
			
			throw new UserNotFound();
			
		}
		
		
	}
	
	
	
	
	
	
	public void  updateMyInfo(int id, String username, String password) throws  RequestFailed{
		
		//get the current loggined user
		
		UserDao userDao = new UserDao();
		
		User user =  userDao.selectUser(id);
		
		//when this throw; it means no user found
		if(user.getId() == 0) {
			
			throw new  RequestFailed();
		}
		
		
		System.out.println(user);
		if(!username.equals("null") ) {
			System.out.println("inside if set new user name ");
			user.setUsername(username);
			
			
		}
		
	if(!password.equals("null") ) {
			
			user.setPassword(password);
		}
		
		System.out.println(user);
	userDao.update(user);
	
	
	
	}
	
	
	
	
	
	
	//manager get all employess info; not  including manager
	
	
	
	
	
	
	public  List <User>  getAllAllEmployeesInfo() throws RequestFailed{
		
	UserDao userDao = new UserDao();
		
		List<User>  usersList=  userDao.getAllUsers();
		
		
		if(usersList == null) {
			
			//meaning no users in user table in database
			throw new RequestFailed();
		}
		return usersList;
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
