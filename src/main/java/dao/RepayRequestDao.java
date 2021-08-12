package dao;

import java.util.List;

import javax.transaction.Transaction;

import org.hibernate.Session;

import model.RepayRequest;
import model.User;
import util.HibernateUtil;

public class RepayRequestDao {

	//user service should add the pending in status prop of the request object  before hit this insert request dao in the 
//	in repay request object must  have the user object which is request holder and has the id in it so hibernate know where to link to user
	//to insert new request in repay request table ; for employee
	public int insert(RepayRequest repayRequest) {
		int idOfNewlycreatedRequest;
		//First we need to open up a session
		Session ses = HibernateUtil.getSession();
		//Then we must start a transaction
		org.hibernate.Transaction tx = ses.beginTransaction();
		//Use the session method .save() to write the super villain object to our database
		idOfNewlycreatedRequest =	(Integer) ses.save(repayRequest);
		//Commit the transaction
		tx.commit();
		//Close the session once you are done
		//ses.close();
		return  idOfNewlycreatedRequest;
	}
	
	//get a specific repay request by repay request  id
	public RepayRequest getASpecificrequestById(int id) {
		Session ses = HibernateUtil.getSession();
		//If you are using ses.get(), you must use the id
		RepayRequest specificRepayRequest = ses.get(RepayRequest.class, id);
		//ses.close();
		return specificRepayRequest;
	}
	
	
	
	//get all repay requests by id for employee with condition pending; for employee; must use the class name instead of table name;
	public  List <RepayRequest>  getAllPendingRepayRequestForAnEmployee(int id) {
		
		
		//HQL Hibernate Query Language creates complex queries using a combo of OOP and SQL
				Session ses = HibernateUtil.getSession();
				List<RepayRequest> pendingRequestsList = ses.createQuery("from RepayRequest where user_fk='" + id + "'" + "and status="+ "'" +"pending'", RepayRequest.class).list();
				if(pendingRequestsList.size() == 0) {
					return null;
				}
				return pendingRequestsList;
		
		
		
		
	}
	
	//get all request for specific employee with condition approved and denied; take id ;for employee
	
	
	public  List <RepayRequest>  getAllApprovedAndDeniedRepayRequestForAnEmployee(int id) {
		
		
		//HQL Hibernate Query Language creates complex queries using a combo of OOP and SQL
				Session ses = HibernateUtil.getSession();
				
				//WHERE user_fk = 26 AND (status = 'approved' or status = 'denied')
				List<RepayRequest> ApprovedAndDeniedRepayRequestList = ses.createQuery("from RepayRequest where user_fk='" + id + "'" + "and (status="+ "'" +"approved'" + "or status=" + "'" +"denied" + "'" +")", RepayRequest.class).list();
				if( ApprovedAndDeniedRepayRequestList.size() == 0) {
					return null;
				}
				return ApprovedAndDeniedRepayRequestList;
		
		
		
		
	}
	
	
	//

	
	
	//update repay request status to be approved using id of employee ; this is for manager
	// repay request service should make the updated repay request either status prop of repay request is approved or denied 
	
	public void update(RepayRequest updatedRepayrequest) {
		Session ses = HibernateUtil.getSession();
		org.hibernate.Transaction tx = ses.beginTransaction();
		ses.update(updatedRepayrequest);
		tx.commit();
	}

	
	
	//get all request from all employee with condition pending status for manager 
	public  List <RepayRequest>  getAllPendingRepayRequestOfAllEmployees() {
		
		
		//HQL Hibernate Query Language creates complex queries using a combo of OOP and SQL
				Session ses = HibernateUtil.getSession();
				List<RepayRequest> pendingRequestsOfAllEmployeesList = ses.createQuery("from RepayRequest where status='"+"pending'", RepayRequest.class).list();
				if(pendingRequestsOfAllEmployeesList.size() == 0) {
					return null;
				}
				return pendingRequestsOfAllEmployeesList;
		
		
		
		
	}
	
	//get all repay  request of an employee ; take in id of employee; for manager 
	
	public  List <RepayRequest>  getAllRepayRequestsOfAnEmployee(int id) {
		
		
		//HQL Hibernate Query Language creates complex queries using a combo of OOP and SQL
				Session ses = HibernateUtil.getSession();
				List<RepayRequest> allRepayRequestsOfAnEmployeeList = ses.createQuery("from RepayRequest where user_fk='" + id + "'" , RepayRequest.class).list();
				if(allRepayRequestsOfAnEmployeeList.size() == 0) {
					return null;
				}
				return allRepayRequestsOfAnEmployeeList;
		
		
		
		
	}
	

	

	
	
	
	
	
	
	
	
	
	public  List <RepayRequest>  getAllApprovedAndDeniedRepayRequestForAllEmployee() {
		
		
		//HQL Hibernate Query Language creates complex queries using a combo of OOP and SQL
				Session ses = HibernateUtil.getSession();
				
			
				List<RepayRequest> ApprovedAndDeniedRepayRequestList = ses.createQuery("from RepayRequest where status="+ "'" +"approved'" + " or status=" + "'" +"denied" + "'", RepayRequest.class).list();
				if( ApprovedAndDeniedRepayRequestList.size() == 0) {
					return null;
				}
				return ApprovedAndDeniedRepayRequestList;
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
