package servlet;

import javax.servlet.http.HttpServlet;


import java.io.IOException;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class MasterJSONServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		System.out.println("Starting in the Master doGet method  master json");
		
		ServletJSONHelper.process(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		System.out.println("Starting in the Master doPost method  master json");
		ServletJSONHelper.process(req, res);
	}
	
	
	
	
	
}
  