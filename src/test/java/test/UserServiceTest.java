package test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dao.RepayRequestDao;
import dao.UserDao;
import exeptions.InvalidCredentials;
import model.RepayRequest;
import model.User;
import service.UserService;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.ArgumentMatchers.anyInt;



import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
public class UserServiceTest {

	@InjectMocks
	public UserService uServ;

	@Mock
	public UserDao userDao;
	
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void testValidLogin() {
		User u1 = new User(1, "test", "user", "testuser", "testpass");
		//User not = new User(0, "test", "user", "testuser", "testpass");
		
		when(userDao.getAuserByUsernameAndPassword(anyString(), anyString())).thenReturn(u1);	
	User loggedIn = uServ.signin("testuser", "testpass");
		

		
			
		
		assertEquals(u1.getId(), loggedIn.getId());
	}
	
	
	
	
	@Test(expected =  InvalidCredentials.class)
	public void testInvalidPassword() {
		//User u1 = new User(1, "test", "user", "testuser", "testpass");
		User not =null;
		
		when(userDao.getAuserByUsernameAndPassword(anyString(), anyString())).thenReturn(not);
		
		uServ.signin("testuser", "testpass");
	}
	
	
	
	@Test
	public void testSubmitRepayRequest() {
		User u1 = new User(1, "test", "user", "testuser", "testpass");
		RepayRequest repayRequest = new RepayRequest(  60,"pending","food", u1);
		int repayrequestId = 6;
		
		RepayRequestDao repayRequestDao = new RepayRequestDao();
		when(repayRequestDao.insert( any())).thenReturn(repayrequestId);	
		when(userDao.selectUser(anyInt())).thenReturn(u1);	

	
	
		
		
		repayrequestId	  = uServ.submitRepayRequest(1,60,"pending"); 
		

		
			
		
		 assertNotEquals(	repayrequestId,  0);
	}
	
	
	
	
	
	
	
	
	
	
	
}
