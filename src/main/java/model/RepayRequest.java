package model;

import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity




@Table(name="repay_request")

public class RepayRequest {

	
	@Id	
	@Column(name="request_id")
	
	@GeneratedValue(strategy=GenerationType.AUTO)
private int id ;


	@Column(name="ticket_number", nullable=false)	
private int ticket_number;


	@Column(name="amount", nullable=false)	
private int amount;


	@Column(name="status", nullable=false)
private String status;

	
	@Column(name="description", nullable=false)

private String description;

	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="User_FK", nullable=false)
	private User requestHolder;
	
	
	
	
	
	
	
	

	public RepayRequest() {
		super();
	}





	public RepayRequest(int id, int ticket_number, int amount, String status, String description, User requestHolder) {
		super();
		this.id = id;
		this.ticket_number = ticket_number;
		this.amount = amount;
		this.status = status;
		this.description = description;
		this.requestHolder = requestHolder;
	}

	
	
	
	
	//constructor to make request ; request holder is the user that make the request ;it should have the user id in it; after login
	//ticket number will be auto genrated
	
	

	public RepayRequest(int amount, String status, String description, User requestHolder) {
	super();
	
	this.ticket_number = (new Random().nextInt(9000) + 1000);
	this.amount = amount;
	this.status = status;
	this.description = description;
	this.requestHolder = requestHolder;
}






	public void makeRepayRequest( int ticket_number, int amount, String status, String description, User requestHolder) {
	
		this.ticket_number = ticket_number;
		this.amount = amount;
		this.status = status;
		this.description = description;
		this.requestHolder = requestHolder;
	}


	public void makeRepayRequestWithId(int id, int ticket_number, int amount, String status, String description, User requestHolder) {
		this.id = id;
		this.ticket_number = ticket_number;
		this.amount = amount;
		this.status = status;
		this.description = description;
		this.requestHolder = requestHolder;
	}



	public int getId() {
		return id;
	}










	public void setId(int id) {
		this.id = id;
	}

	public int getTicket_number() {
		return ticket_number;
	}

	public void setTicket_number(int ticket_number) {
		this.ticket_number = ticket_number;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getRequestHolder() {
		return requestHolder;
	}

	public void setRequestHolder(User requestHolder) {
		this.requestHolder = requestHolder;
	}








	@Override
	public String toString() {
		return "RepayRequest [id=" + id + ", ticket_number=" + ticket_number + ", amount=" + amount + ", status="
				+ status + ", description=" + description + ", requestHolder=" + requestHolder.getUsername() + "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
