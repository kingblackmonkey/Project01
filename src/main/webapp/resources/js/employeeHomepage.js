/**
 * 
 */
 
		//here thats is why i have to go to homepage again to refresh to fix the session bug
	console.log('refresh')
window.onload = function() {
    //considering there aren't any hashes in the urls already
    if(!window.location.hash) {
        //setting window location
        window.location = window.location + '#loaded';
        //using reload() method to reload web page
        window.location.reload();
    }
}
 
 function domSelectors(){
	
	let container = document.getElementsByClassName("container-holder")[0];
	
	let homePagebtn =  document.getElementsByClassName("home-page-btn")[0];
	let myInfobtn =  document.getElementsByClassName("my-info-btn")[0];
	let submitRequestbtn =  document.getElementsByClassName("submit-repay-request-btn")[0];
	let viewpendingRequestbtn =  document.getElementsByClassName("view-pending-request-btn")[0];
	let viewResolvedRequestbtn =  document.getElementsByClassName("view-resolved-request-btn")[0];
	
	
	
	let formSubmitRequest =  document.getElementsByClassName("form-submit-request")[0];
	
	let requestAmountInput =   document.getElementById('inlineFormInputName2');
	
	let requestCategory = document.getElementById('inlineFormCustomSelectPref');
	
	let requestTable = document.getElementById('repay-request-table');
	
let tableContainer = document.getElementById("table-request-container");
	
	return { 
		container, 
		formSubmitRequest ,
		 homePagebtn , 
		  myInfobtn,  
		  submitRequestbtn,  
		  viewpendingRequestbtn,  
		  viewResolvedRequestbtn ,
		   requestAmountInput,
		  requestCategory,
		  requestTable,
		   tableContainer,
		  
		        };
}
 
 
 //generate row for table
 
 function populateTableRequest(data){
	
	
		let container= 	 domSelectors().container;
		
	
					
								container.innerHTML= " ";
		//serve the request table
		
								 

					let tableRequest = 		`
					
					
					
					               <table class="table table-dark "  id="repay-request-table">
 									         	 <thead>
  										            <tr>
  											          <th scope="col">ticket_number</th>
   													   <th scope="col">amount</th>
   										  		 	 <th scope="col">status</th>
  										 				   <th scope="col">description</th>
  													  </tr>
  													</thead>
 															 <tbody id="table-request-container">
  																
  															 
																		   																			 <tr>
															
  															</tbody>
										</table>
					
					
					
					`	;
					
						container.innerHTML=  tableRequest;
						
						
						//must select the table container after it mount to the dom;
							let tableRquestContainer = domSelectors().tableContainer;
						
						let rowStr="";
						//populate row for table request 
						for (r of data) {
  							rowStr =  rowStr + `
  								
  								 <tr>
    								  <th scope="row">${r.ticket_number} </th>
    									  <td>${r.amount}</td>
    									  <td>${r.status}</td>
     										 <td>${r.description}</td>
   										 </tr>
  								
  							
  							
  								`
  								
  									
						};
						
							tableRquestContainer.innerHTML= 	rowStr;
	
};
 
 
 
 //add usernane to employee home page
async function populateHomePageUsername() {
  
try{
 let res =  await axios({
   method: 'get',
  url: 'http://localhost:8080/RepayHub/api/employee/info',
  
  
 });
		
		console.log('in the populate username function')
		console.log(res.data.username);
		
     
        
        
        let usernameHolder = document.getElementById("usernameHolder");
         usernameHolder.innerHTML= res.data.username;
		
	} catch(e){
	
	
	
	console.log(e.response.data.errorMessage);
		
		alert(e.response.data.errorMessage);
		return;
	}



	}

	 populateHomePageUsername();

        
 


//employee info call back


async  function populateEmployeeInfo(){
		
		//click myinfo btn 
		//make a request to get my info
		
			try{
		
		
	
					
							 let res =  await axios({
 											 
									  url: 'http://localhost:8080/RepayHub/api/employee/info',
 											
  
															 });
															 
									console.log(res.data);						 

									//populate the table 
									
						let userInfoTable =			` <table class="table table-dark user-info-table">
 											 <thead>
  												  <tr>
   											       <th scope="col">User Role</th>
  												    <th scope="col">First Name</th>
 											         <th scope="col">Last Name</th>
   													  <th scope="col">UserName</th>
  												 </tr>
 													 </thead>
 												 <tbody>
 												   <tr>
  											                  <th scope="row">${res.data.userRole}</th>
   															   <td>${res.data.firstname}</td>
   															   <td>${res.data.lastname}</td>
  															    <td> ${res.data.username}       </td>
   												     </tr>
 
  												</tbody>
											</table>`;
								let container= 	 domSelectors().container;
								
								container.innerHTML= " ";
								container.innerHTML=  userInfoTable ;
							
									// display to employee	page
									
									
	
	
	} catch(e){
	
	
	
	
		
		alert(e.response.data.errorMessage);
		return;
	}	
	
	
	};




//send repay request call back 
async function  sendRepayRequest(){
	
	//serve the repay request form
	let repayRequestForm = `      
	
<form class="form-submit-request">
	<div class="form-row align-items-baseline">
	    <div class="col-sm-5 ">
	    
	   			 <label class="sr-only" for="inlineFormInputName2">Name</label>
  			  	<input type="number" class="form-control mb-2 mr-sm-2 request-amount-input" id="inlineFormInputName2" placeholder="Request Amount"   required>

 		</div>

 		<div class="col-sm-5 ">
 			
 				<select class="custom-select repay-request-category" id="inlineFormCustomSelectPref">
    			<option selected>Category</option>
   				 <option value="food">Food</option>
   				 <option value="travel">Travel</option>
   					 <option value="lodging">Lodging</option>
   					  <option value="other">Other</option>
 			 </select>
  
 			
 			</div>
 			
 			 <div class="col-sm-2">
    			  <button type="submit" class="btn-sm btn-primary">Submit</button>
  			</div>
  		
	</div>

</form>
	
	
	
	
	` ;
	
		let container= 	 domSelectors().container;
								
								container.innerHTML= " ";
								container.innerHTML= repayRequestForm ;
	//get the value ; 
	
	let formSubmitRequest =  domSelectors().formSubmitRequest;
	
	formSubmitRequest.addEventListener("submit", async function(e){
		
		e.preventDefault();
		
		let requestAmountInput =  domSelectors().requestAmountInput;
		let requestCategory =  domSelectors().requestCategory;
		
	let requestAmountNumber =  requestAmountInput.value;
	let requestCategoryString = requestCategory.value;

//check to make sure there is value for moth amoutn and category

if(!requestAmountNumber || requestCategoryString == "Category"){
	
	alert("Please Select a specific Category ");
	return;
}

	let request = {

               "amount": requestAmountNumber,
                 "category": requestCategoryString

		

};
		
		console.log(request);
		
			//send request
		
		
		
			try{
		
		
	
					
							 let res =  await axios({
 										method: 'post',	 
									  url: 'http://localhost:8080/RepayHub/api/employee/submit/request',
 										 data:	 request
  
  
														
															 });
								
								//reset the form
										 requestAmountInput.value = "";
										 requestCategory.value = "";				 
								alert(res.data.message);						 

								
					
									
	
	
	} catch(e){
	
	
	
	
		
		alert(e.response.data.errorMessage);
		return;
	}	
	
		
		
		
		
	});
	
	

};





//populate home page

function populateHomePage(){
	
	
	let container= 	 domSelectors().container;
								
								container.innerHTML= " ";
								container.innerHTML= `<h1 class="home-page-text"> Welcome </h1>` ;
	
};
    
 //send  pending request and make call to populate request table
 
   async function getPendingRequest(){
	
	//send get request
	
	
		try{
		
	
			
							 let res =  await axios({
 										
									  url: 'http://localhost:8080/RepayHub/api/employee/view/pending/request',
 										
  
  
														
															 });
								
								console.log(res.data);
							//popualate table for pending request
							populateTableRequest(res.data )	;
									
	
		
	
			
						
					
									
	
	
	} catch(e){
	
	
	
	
		
		alert(e.response.data.errorMessage);
		return;
	}	
	
	
}; 
    
    
    
  //send resolved request and populate   request table
    
    
    async function getResolvedRequest (){
	
		try{
		
	
			
							 let res =  await axios({
 										
									  url: 'http://localhost:8080/RepayHub/api/employee/view/approvedAndDenied/request',
 										
  
  
														
															 });
								
								console.log(res.data);
							//popualate table for pending request
							populateTableRequest(res.data )	;
									
	
		
	
			
						
					
									
	
	
	} catch(e){
	
	
	
	
		
		alert(e.response.data.errorMessage);
		return;
	}	
	
	
};
    
    
    
    
    
    //add all event listener to all nav buttons
function addAlleventsToNavBtns () {
	
	                 
	let myInfobtn =  domSelectors().myInfobtn;
   

//employee home page
	let homeBtn =  domSelectors().homePagebtn;
	homeBtn.addEventListener('click',populateHomePage)
	
	
//employee info event
	 myInfobtn.addEventListener('click', populateEmployeeInfo);
	
//employee 	submit payment event
 //
	let submitPaymentRequestBtn =    domSelectors().submitRequestbtn;
	
	submitPaymentRequestBtn.addEventListener('click', sendRepayRequest); 
	
	//employee view pending request
	
	let viewPendingBtn =  domSelectors().viewpendingRequestbtn;
	
				viewPendingBtn.addEventListener('click',getPendingRequest);
				
				
//employee view resolved request event

let viewResolvedRequest = 		  domSelectors().viewResolvedRequestbtn;

			viewResolvedRequest.addEventListener('click',getResolvedRequest);

		
};


 addAlleventsToNavBtns();
