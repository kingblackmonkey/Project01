package dao;



import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import model.RepayRequest;
import model.User;
import util.HibernateUtil;

public class UserDao {

	
	
	//get a user by  username and  pass word ;will return a user object if found ; if no will return null ;for employee
	
	public User getAuserByUsernameAndPassword (String username, String password) {
		

			
			//this fuction will return one user in user table but it put in a list ; so extract that user out by index 0
			//HQL Hibernate Query Language creates complex queries using a combo of OOP and SQL
					Session ses = HibernateUtil.getSession();
					List<User> userList = ses.createQuery("from User where username='" +  username + "'" + "and password="+ "'" + password +"'"   ,User.class).list();
					//if no user in a list 
					if(userList.size() == 0) {
						return null;
					}
					//yes then return the user ; the first item in the list
					return userList.get(0);
			
			
			
			
	}

		
		

		
	
	

	
	//; for employee   ; to insert employee in data base
	
	public void insert(User user) {
		//First we need to open up a session
		Session ses = HibernateUtil.getSession();
		//Then we must start a transaction
		org.hibernate.Transaction tx = ses.beginTransaction();
		//Use the session method .save() to write the super villain object to our database
		ses.save(user);
		//Commit the transaction
		tx.commit();
		//Close the session once you are done
		//ses.close();
	}
	
	
	//get user by id ; for employee
	public User selectUser(int id) {
		Session ses = HibernateUtil.getSession();
		//If you are using ses.get(), you must use the id
		 User user = ses.get( User.class, id);
		//ses.close();
		return user;
	}
	
	
	// for employee update should use same number of fields ; username and pass word ; usersrive should receive the new username and password  ; for employee
	public void update(User user) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		ses.update(user);
		tx.commit();
	}
	
	
	
	
	//get all users from user table; this is for manager;  not including manager
	public List<User> getAllUsers () {
		


				Session ses = HibernateUtil.getSession();
				List<User> usersList = ses.createQuery(" from User    where user_type_fk='20' "   ,User.class).list();
				//if no user in a list 
				if(usersList.size() == 0) {
					return null;
				}
				//yes then return the list  of users ; 
				return usersList;
		
		
		
		
}
	
}
