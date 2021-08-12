package service;

import java.util.ArrayList;
import java.util.List;

import dao.RepayRequestDao;
import exeptions.InvalidCredentials;
import exeptions.RequestFailed;
import model.RepayRequest;
import model.User;

public class RepayRequestService {
	
	//for employee

	public	 List <RepayRequest> viewMyPendingRequest (int id) throws  RequestFailed{
		
		RepayRequestDao repayRequestDao = new  RepayRequestDao ();
		
		 List <RepayRequest> pendingRequestsListForJson = new ArrayList<RepayRequest>();
		
		List<RepayRequest>  pendingRequestsList  =   repayRequestDao.getAllPendingRepayRequestForAnEmployee(id) ;
		if ( pendingRequestsList  != null) {
			
			for (int i = 0; i <  pendingRequestsList.size(); i++) {
			
				int ticket   = pendingRequestsList.get(i).getTicket_number()   ;
				int amount = pendingRequestsList.get(i).getAmount();
				String status = pendingRequestsList.get(i).getStatus();
				String description = pendingRequestsList.get(i).getDescription();
				 User user = pendingRequestsList.get(i).getRequestHolder();
				 String userName =  pendingRequestsList.get(i).getRequestHolder().getUsername();
				 
				RepayRequest repayRequest =new RepayRequest();
	
				
				
				//make new user with out repay request list to break the chain; to  be able to send back to client without infinite loop
				//repay request id 1 has me; me hold a list which has repay request 1 in it ; it causes the loop
				//when the loop hapen ; you can not send back to client ;
				//user must be there in the request holder so it can call get username; java want to call it; or your code stop
				
				user = new User(userName);
		      	repayRequest.makeRepayRequest(ticket, amount, status, description, user);
			
				 pendingRequestsListForJson.add(repayRequest);
			}
			


		

			return pendingRequestsListForJson;
		
		
		}else {
			throw new RequestFailed();
			
		}
		
		
	}
	
	
	//for employee
	
	

	public	 List <RepayRequest> viewAllMyApprovedAndDeniedRequest (int id) throws  RequestFailed{
		
		RepayRequestDao repayRequestDao = new  RepayRequestDao ();
		
		 List <RepayRequest> AllMyApprovedAndDeniedRequestListForJson = new ArrayList<RepayRequest>();
		
		List<RepayRequest>  AllMyApprovedAndDeniedRequestList  =   repayRequestDao.getAllApprovedAndDeniedRepayRequestForAnEmployee(id) ;
		if (AllMyApprovedAndDeniedRequestList  != null) {
			
			for (int i = 0; i < AllMyApprovedAndDeniedRequestList.size(); i++) {
			
				int ticket   = AllMyApprovedAndDeniedRequestList.get(i).getTicket_number()   ;
				int amount = AllMyApprovedAndDeniedRequestList.get(i).getAmount();
				String status =AllMyApprovedAndDeniedRequestList.get(i).getStatus();
				String description = AllMyApprovedAndDeniedRequestList.get(i).getDescription();
				 User user = AllMyApprovedAndDeniedRequestList.get(i).getRequestHolder();
				 String userName = AllMyApprovedAndDeniedRequestList.get(i).getRequestHolder().getUsername();
				 
				RepayRequest repayRequest =new RepayRequest();
	
				
				
				//make new user with out repay request list to break the chain; to  be able to send back to client without infinite loop
				//repay request id 1 has me; me hold a list which has repay request 1 in it ; it causes the loop
				//when the loop hapen ; you can not send back to client ;
				//user must be there in the request holder so it can call get username; java want to call it; or your code stop
				
				user = new User(userName);
		      	repayRequest.makeRepayRequest(ticket, amount, status, description, user);
			
		      	AllMyApprovedAndDeniedRequestListForJson.add(repayRequest);
			}
			


		

			return AllMyApprovedAndDeniedRequestListForJson;
		
		
		}else {
			throw new RequestFailed();
			
		}
		
		
	}
	
	
	
	
	
	//for manager
	public void approveRepayRequest(int id )throws  RequestFailed {
		
		//get the specific request based on id 
		
		
		RepayRequestDao repayRequestDao = new RepayRequestDao();
		
		
		
		RepayRequest currentRepayRequest = repayRequestDao.getASpecificrequestById(id); 
		
		if(currentRepayRequest == null) {
			
			throw new RequestFailed();
		}
		
		 currentRepayRequest.setStatus("approved");
		 
		 repayRequestDao.update(currentRepayRequest);
		
		
		
	}
	
	
	
	
	
	public void denyRepayRequest(int id )throws  RequestFailed {
		
		//get the specific request based on id 
		
		
		RepayRequestDao repayRequestDao = new RepayRequestDao();
		
		
		
		RepayRequest currentRepayRequest = repayRequestDao.getASpecificrequestById(id); 
		
		if(currentRepayRequest == null) {
			
			throw new RequestFailed();
		}
		
		 currentRepayRequest.setStatus("denied");
		 
		 repayRequestDao.update(currentRepayRequest);
		
		
		
	}
	
	
	
	
	
	
	
	//set the id so when manger approve or denied it has the id in it ; this function will run before
	//to get all pending requests with id in it so manager can see;
	//then when manger approve or deny we just need to send back the array of ids ;
	//so the repay request has the id matching will be updated
	
public	 List <RepayRequest> viewPendingRequestOfAllEmployees () throws  RequestFailed{
		
		RepayRequestDao repayRequestDao = new  RepayRequestDao ();
		
		 List <RepayRequest> pendingRequestsListForJson = new ArrayList<RepayRequest>();
		
		List<RepayRequest>  pendingRequestsList  =   repayRequestDao.getAllPendingRepayRequestOfAllEmployees();
		if ( pendingRequestsList  != null) {
			
			for (int i = 0; i <  pendingRequestsList.size(); i++) {
				int id =  pendingRequestsList.get(i).getId();
				int ticket   = pendingRequestsList.get(i).getTicket_number()   ;
				int amount = pendingRequestsList.get(i).getAmount();
				String status = pendingRequestsList.get(i).getStatus();
				String description = pendingRequestsList.get(i).getDescription();
				 User user = pendingRequestsList.get(i).getRequestHolder();
				 String userName =  pendingRequestsList.get(i).getRequestHolder().getUsername();
				 
				RepayRequest repayRequest =new RepayRequest();
	
				
				
				//make new user with out repay request list to break the chain; to  be able to send back to client without infinite loop
				//repay request id 1 has me; me hold a list which has repay request 1 in it ; it causes the loop
				//when the loop hapen ; you can not send back to client ;
				//user must be there in the request holder so it can call get username; java want to call it; or your code stop
				
				user = new User(userName); 
		      	repayRequest.makeRepayRequestWithId(  id ,ticket, amount, status, description, user);
			
				 pendingRequestsListForJson.add(repayRequest);
			}
			


		

			return pendingRequestsListForJson;
		
		
		}else {
			throw new RequestFailed();
			
		}
		
	
		
}	
		
		
		
		
		
		public	 List <RepayRequest> viewAllApprovedDeniedOfAllEmployees () throws  RequestFailed{
			
			RepayRequestDao repayRequestDao = new  RepayRequestDao ();
			
			 List <RepayRequest> requestsListForJson = new ArrayList<RepayRequest>();
			
			List<RepayRequest>  pendingRequestsList  =   repayRequestDao.getAllApprovedAndDeniedRepayRequestForAllEmployee();
			if ( pendingRequestsList  != null) {
				
				for (int i = 0; i <  pendingRequestsList.size(); i++) {
				
					int ticket   = pendingRequestsList.get(i).getTicket_number()   ;
					int amount = pendingRequestsList.get(i).getAmount();
					String status = pendingRequestsList.get(i).getStatus();
					String description = pendingRequestsList.get(i).getDescription();
					 User user = pendingRequestsList.get(i).getRequestHolder();
					 String userName =  pendingRequestsList.get(i).getRequestHolder().getUsername();
					 
					RepayRequest repayRequest =new RepayRequest();
		
					
					
					//make new user with out repay request list to break the chain; to  be able to send back to client without infinite loop
					//repay request id 1 has me; me hold a list which has repay request 1 in it ; it causes the loop
					//when the loop hapen ; you can not send back to client ;
					//user must be there in the request holder so it can call get username; java want to call it; or your code stop
					
					user = new User(userName);
			      	repayRequest.makeRepayRequest(ticket, amount, status, description, user);
				
			      	requestsListForJson.add(repayRequest);
				}
				


			

				return requestsListForJson;
			
			
			}else {
				throw new RequestFailed();
				
			}
			
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	//for manager
	
		public	 List <RepayRequest> viewAllRepayRequestOfAnEmployee (int id) throws  RequestFailed{
			
			
			
			
			
			
			RepayRequestDao repayRequestDao = new  RepayRequestDao ();
			
			 List <RepayRequest> requestsListForJson = new ArrayList<RepayRequest>();
			
			List<RepayRequest>  pendingRequestsList  =   repayRequestDao.getAllRepayRequestsOfAnEmployee(id);
			if ( pendingRequestsList  != null) {
				
				for (int i = 0; i <  pendingRequestsList.size(); i++) {
				
					int ticket   = pendingRequestsList.get(i).getTicket_number()   ;
					int amount = pendingRequestsList.get(i).getAmount();
					String status = pendingRequestsList.get(i).getStatus();
					String description = pendingRequestsList.get(i).getDescription();
					 User user = pendingRequestsList.get(i).getRequestHolder();
					 String userName =  pendingRequestsList.get(i).getRequestHolder().getUsername();
					 
					RepayRequest repayRequest =new RepayRequest();
		
					
					
					//make new user with out repay request list to break the chain; to  be able to send back to client without infinite loop
					//repay request id 1 has me; me hold a list which has repay request 1 in it ; it causes the loop
					//when the loop hapen ; you can not send back to client ;
					//user must be there in the request holder so it can call get username; java want to call it; or your code stop
					
					user = new User(userName);
			      	repayRequest.makeRepayRequest(ticket, amount, status, description, user);
				
			      	requestsListForJson.add(repayRequest);
				}
				


			

				return requestsListForJson;
			
			
			}else {
				throw new RequestFailed();
				
			}
			
		
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		}	
	
	
	
	
	
	
		
	
	
	
	
	
	
	
	
	
	
}
