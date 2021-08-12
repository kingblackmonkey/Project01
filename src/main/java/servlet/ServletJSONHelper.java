package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;

import   controllers.LoginJsonController;

public class ServletJSONHelper {

	
	
	public static void process(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException{
		System.out.println("In the ServletJSONHelper with URI: " + req.getRequestURI());
		switch(req.getRequestURI()) {
			case "/RepayHub/api/login":
				 LoginJsonController.login(req, res);
				break;
			case "/RepayHub/api/employee/info":
		 		LoginJsonController.getEmployeeInfo(req, res);
				break;
			case "/RepayHub/api/employee/submit/request":
				LoginJsonController.submitRepayRequest(req, res);
				break;
			case "/RepayHub/api/employee/view/pending/request":
				LoginJsonController.viewAllMyPendingRepayRequests(req, res);
				break;
				
			case "/RepayHub/api/employee/view/approvedAndDenied/request":
				LoginJsonController.viewAllMyApproveAndDeniedRepayRequests(req, res);
				break;	
				
			case "/RepayHub/api/employee/update/info":
				LoginJsonController.updateMyInfo(req, res);
				break;		
				
				//for manager
			case "/RepayHub/api/manager/approve/repayrequest":
				LoginJsonController.approveRepayRequest(req, res);
				break;		
				
			case "/RepayHub/api/manager/deny/repayrequest":
				LoginJsonController.denyRepayRequest(req, res);
				break;			
				
			case "/RepayHub/api/manager/view/pendingrepayrequests":
				LoginJsonController.viewAllPendingOfAllEmployees(req, res);
				break;	
				
			case "/RepayHub/api/manager/view/approvedDeniedRepayrequests":
				LoginJsonController.viewAllApprovedDeniedOfAllEmployees(req, res);
				break;		
				
				
			case "/RepayHub/api/manager/view/allRequestOfAnEmployee":
				LoginJsonController.viewAllRepayRequestOfAanEmployee(req, res);
				break;		
				
				
			case "/RepayHub/api/manager/view/allEmployeesInfo":
				LoginJsonController.viewAllEmployeesInfo(req, res);
				break;		
			case "/RepayHub/api/session":
				LoginJsonController.getSession(req, res);
				break;	
				
			case "/RepayHub/api/logout":
				LoginJsonController.logout(req, res);
				break;		
		}
	}
	
	
	
	
	
	
	
	
	
	
}
