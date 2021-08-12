package controllers;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginViewController {

	
	public static String fetchLoginPage(HttpServletRequest req) throws ServletException, IOException {
		System.out.println(req.getContentType());
		System.out.println("This should return the view login.html");
		
	//	System.out.println(req.getSession().getAttribute("id"));
		//System.out.println(req.getSession().getAttribute("userrole"));
		return "resources/html/loginPage.html";

	}
	
	
	public static String fetchHomePage(HttpServletRequest req) throws ServletException, IOException {
		//System.out.println(req.getContentType());
		System.out.println("This should return the view Homepage.html either for employee or manager");
		
		System.err.println(req.getSession().getAttribute("id"));
		System.err.println(req.getSession().getAttribute("userrole"));
		
		if (req.getSession().getAttribute("id")!=null && req.getSession().getAttribute("userrole").equals("employee")   ) {
			System.out.println("in return employee homepage");
			
			return "resources/html/employeeHomepage.html";
		}else if (req.getSession().getAttribute("id")!=null && req.getSession().getAttribute("userrole").equals("manager")){
			
			
			System.out.println("in return manager homepage");
			return "resources/html/managerHomepage.html";
			
		}else {
			System.out.println("in return session expired page");
			return "resources/html/sessionExpired.html";
		}
		

	}
	
	
}
