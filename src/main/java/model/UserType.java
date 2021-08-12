package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity




@Table(name="user_type")

public class UserType {

	
	@Id	
	@Column(name="user_type_id")
	
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id ;
	
	@Column(name="usertype", nullable=false)	
private String usertype;
	
	@OneToMany(mappedBy="userTypeHolder", fetch=FetchType.LAZY)
	
	private List<User> userByTypeList = new ArrayList<User>();


	
	
	
	
	//to create user type object
	
	
		public UserType() {
		super();
	}


		public UserType(String usertype) {
		super();
		this.usertype = usertype;
	}

	
//to receive user type object form db; the field userbytype list will be set in user service after we query list of users of same type and will set it using setter of this usertype object
		
		
		public UserType(int id, String usertype) {
			super();
			this.id = id;
			this.usertype = usertype;
		}



	//to create the  usertype with id already exist in data base	
		
		
		
		
		public UserType(int id) {
			super();
			this.id = id;
		}

		
		
		
		
		
		
		
		
	public int getId() {
		return id;
	}








	public void setId(int id) {
		this.id = id;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public List<User> getUserTypeList() {
		return userByTypeList;
	}

	public void setUserTypeList(List<User> userByTypeList) {
		this.userByTypeList = userByTypeList;
	}
	
	
	
	@Override
	public String toString() {
		return "UserType [id=" + id + ", usertype=" + usertype + ", userTypeList=" + userByTypeList.size() + "]";
	}

	
}
