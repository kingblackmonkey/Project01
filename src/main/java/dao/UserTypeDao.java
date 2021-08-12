package dao;

import org.hibernate.Session;

import model.User;
import model.UserType;
import util.HibernateUtil;

public class UserTypeDao {

	
	
	
	public void insert(UserType usertype) {
		//First we need to open up a session
		Session ses = HibernateUtil.getSession();
		//Then we must start a transaction
		org.hibernate.Transaction tx = ses.beginTransaction();
		//Use the session method .save() to write the super villain object to our database
		ses.save(usertype);
		//Commit the transaction
		tx.commit();
		//Close the session once you are done
		//ses.close();
	}
	
	
	//id is the user typpe holder (forign key in the user object or later can be found in session ; will be set in session also); it gonnaa be the primary key in the usertype table
	public UserType selectUserTypeById(int id) {
		Session ses = HibernateUtil.getSession();
		//If you are using ses.get(), you must use the id
		UserType usertype = ses.get( UserType.class, id);
		//ses.close();
		return usertype;
	}
	
	
}
