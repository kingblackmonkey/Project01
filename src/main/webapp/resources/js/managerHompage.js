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
 //dom selectors
 console.log(12344)
 function domeSelectores(){
	
	
		let container = document.getElementsByClassName("container-holder")[0];
	
	let homePagebtn =  document.getElementsByClassName("home-page-btn")[0];
		let approveBtn =  document.getElementsByClassName("approve-request-btn")[0];	
		let denyBtn =  document.getElementsByClassName("deny-request-btn")[0];
			let viewResolvedBtn =  document.getElementsByClassName("view-resolved-request-btn")[0];
				let viewEmployeeInfoBtn =  document.getElementsByClassName("view-employees-info-btn")[0];
					let viewEmployeeRequestBtn =  document.getElementsByClassName("view-employee-request-btn")[0];
						let logOutBtn =  document.getElementsByClassName("log-out-btn")[0];	
						let approvetBtnInTable = document.getElementById('approve-button');
							let denyBtnInTable =  document.getElementById('deny-button');
	
	return {
		
		 container, homePagebtn  , approveBtn  , denyBtn   ,  viewResolvedBtn ,  viewEmployeeInfoBtn ,viewEmployeeRequestBtn  ,  logOutBtn , approvetBtnInTable, denyBtnInTable
		
		
		
	};
};


function populateApproveOrDenyRequestTable(tableStr, data, tableBodyContainerId){
	
	try{
		
			
	//select page container
	let container =  domeSelectores().container;
	//serve the table on the page
	container.innerHTML = "";
	container.innerHTML = tableStr;
	
	//select table body container
let tablebody = 	document.getElementById(tableBodyContainerId);
	
	console.log(tablebody)
	//extract data and put in table body container 
	
		let rowStr="";
						//populate row for table request 
						for (r of data) {
  							rowStr =  rowStr + `
  								
  						    <tr >
    							  <th scope="row"> <input type="checkbox" id=${r.id} class="input-request-checkbox"/>   ${r.ticket_number}</th>
							      <td> ${r.amount}</td>
      								<td>${r.status}</td>
      								<td>${r.description}</td>
        						<td>${r.requestHolder. username}</td>
        
   							 </tr>
  							
  							
  								`
  								
  									
						};
						
					 tablebody.innerHTML = rowStr;
						
		
	}catch (e){
		
		console.log(e);
		
		
		
		
		
	}

	
};

 //-----------------------------------------------------------------------------------------------
 //aprove request
 
 async function approveRequest(){
	

	
	try{
		
		
			//send request to get all peding request of all employee
	
	let res =  await axios({
   method: 'get',
  url: 'http://localhost:8080/RepayHub/api/manager/view/pendingrepayrequests',
  
  
 });
	console.log(res);
	
	//display all pending request with input check box on the page
	
	let tableStr = `
	<div  class="container-fluid">
	 <table class="table table-dark "  id="approve-request-table">
 			 <thead>
   				 <tr>
     				 <th scope="col">Ticket_number</th>
    				  <th scope="col">Amount</th>
    				  <th scope="col">Status</th>
    				  <th scope="col">Description</th>
    				  <th scope="col">Request Holder</th>
  				  </tr>
  				</thead>
 				 <tbody id="approve-request-table-container">
   		
   
  				</tbody>
		</table>
		<div class= "d-flex justify-content-end">
			<button type="button" class="btn btn-primary btn-sm   btn-default text-right  "  id = "approve-button">Approve</button>
		</div>
		
		
	</div>
	
	
	`;
	
	
	
	
	
	
	populateApproveOrDenyRequestTable(tableStr,res.data,'approve-request-table-container' );
	//add event listener to approve btn 
	
	 let aprroveBtnIntable = domeSelectores().approvetBtnInTable;
	 aprroveBtnIntable.addEventListener('click',async function(){
		
		
		//get the all id out of input checked
		let  arrayOfrequestIds = []
let  checkboxes = document.querySelectorAll('input[type=checkbox]:checked')


for (let i = 0; i < checkboxes.length; i++) {
   arrayOfrequestIds.push(checkboxes[i].id);
}
		
		if(checkboxes.length == 0){
			
			
			alert("Please select request to approve");
			return;
		}
		//send request to approve selected  request
			let res =  await axios({
  					 method: 'post',
 						 url: 'http://localhost:8080/RepayHub/api/manager/approve/repayrequest',
  
  						data:  arrayOfrequestIds
 									});
		//alert sucessful request message
				alert(res.data.message);	
				
				location.href = 'http://localhost:8080/RepayHub/homepage';
		
	});
		
	}catch (e){
		
		console.log(e);
		alert(e.response.data.errorMessage);
		
	}
	
	
};
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/// deny request

async function denyRequest(){
	
	try{
		
		
			//send request to get all peding request of all employee
	
	let res =  await axios({
   method: 'get',
  url: 'http://localhost:8080/RepayHub/api/manager/view/pendingrepayrequests',
  
  
 });
	console.log(res);
	
	//display all pending request with input check box on the page
	
	let tableStr = `
	<div  class="container-fluid">
	 <table class="table table-dark "  id="deny-request-table">
 			 <thead>
   				 <tr>
     				 <th scope="col">Ticket_number</th>
    				  <th scope="col">Amount</th>
    				  <th scope="col">Status</th>
    				  <th scope="col">Description</th>
    				  <th scope="col">Request Holder</th>
  				  </tr>
  				</thead>
 				 <tbody id="deny-request-table-container">
   		
   
  				</tbody>
		</table>
		<div class= "d-flex justify-content-end">
			<button type="button" class="btn  btn-danger btn-sm   btn-default text-right  "  id = "deny-button">Deny</button>
		</div>
		
		
	</div>
	
	
	`;
	
	
	
	
	
	
	populateApproveOrDenyRequestTable(tableStr,res.data,'deny-request-table-container' );
	//add event listener to deny btn 
	
	 let denyBtnIntable = domeSelectores().denyBtnInTable;
	denyBtnIntable.addEventListener('click',async function(){
		
		
		//get the all id out of input checked
		let  arrayOfrequestIds = []
let  checkboxes = document.querySelectorAll('input[type=checkbox]:checked')


for (let i = 0; i < checkboxes.length; i++) {
   arrayOfrequestIds.push(checkboxes[i].id);
}
		
		if(checkboxes.length == 0){
			
			
			alert("Please select request to deny");
			return;
		}
		//send request to deny selected  request
			let res =  await axios({
  					 method: 'post',
 						 url: 'http://localhost:8080/RepayHub/api/manager/deny/repayrequest',
  
  						data:  arrayOfrequestIds
 									});
		//alert sucessful request message
				alert(res.data.message);	
				
				location.href = 'http://localhost:8080/RepayHub/homepage';
		
	});
		
	}catch (e){
		
		console.log(e);
		alert(e.response.data.errorMessage);
		
	}
	
	
};






//---------------------------------------------


 function populateTable(data){
	
	
 //populate request function without input in the table
 
 try{}catch(e){console.log(e)}
 
 let tableStr = `
 
 
 <table class="table table-dark"  id="request-table">
<thead>
      <tr>
        <th scope="col">Ticket_number</th>
        <th scope="col">Amount</th>
        <th scope="col">Status</th>
        <th scope="col">Description</th>
        <th scope="col">Request Holder</th>
      </tr>
    </thead>
    <tbody id="request-table-container">


    </tbody>
</table>
 
 `
	
		

	
	
	;
	
 let container =  domeSelectores().container;

 console.log(container)
   container.innerHTML = " ";
 
 container.innerHTML=  tableStr;
 
 let tablebody = 	document.getElementById('request-table-container');
	
	console.log(tableStr )
	//extract data and put in table body container 
	
		let rowStr="";
						//populate row for table request 
						for (r of data) {
  							rowStr =  rowStr + `
  								
  						    <tr >
    							  <th scope="row">   ${r.ticket_number}</th>
							      <td> ${r.amount}</td>
      								<td>${r.status}</td>
      								<td>${r.description}</td>
        						<td>${r.requestHolder. username}</td>
        
   							 </tr>
  							
  							
  								`
  								
  									
						};
						
					 tablebody.innerHTML = rowStr;
						
 
 
	
	
};
 







//view resolved request

async function  viewResolvedRequest (){


//send request


	let res =  await axios({
   method: 'get',
  url: 'http://localhost:8080/RepayHub/api/manager/view/approvedDeniedRepayrequests',
  
  
 });
 
 
 
 console.log(res)
 
 

//populate table 

 populateTable(res.data)

};

//--------------------------------------------------------------------------------------



//view all employee



async function viewAllemployee(){
	
try{
	
	
		let res =  await axios({
   method: 'get',
  url: 'http://localhost:8080/RepayHub/api/manager/view/allEmployeesInfo',
  
  
 });
 
	
	
	
			let userInfoTable =			` <table class="table table-dark user-info-table">
 											 <thead>
  												  <tr>
   											       <th scope="col">User Role</th>
  												    <th scope="col">First Name</th>
 											         <th scope="col">Last Name</th>
   													  <th scope="col">UserName</th>
  												 </tr>
 													 </thead>
 												 <tbody id= "employees-info-table-container">
 									
 
  												</tbody>
											</table>`;
								let container= 	domeSelectores().container;
								
								container.innerHTML= " ";
								container.innerHTML=  userInfoTable ;
							
	
	//add row to table for empployeess infos
	
							let tableContainer   = 		document.getElementById('employees-info-table-container');
	
		
		let rowStr="";
						//populate row for table request 
						for (r of res.data) {
  							rowStr =  rowStr + `
  								
  					
  								
															   <tr>
  											                  <th scope="row">${r.userRole}</th>
   															   <td>${r.firstname}</td>
   															   <td>${r.lastname}</td>
  															    <td> ${r.username}       </td>
   												     </tr>
	
	
  							
  								`
  								
  									
						};
						

	
	tableContainer.innerHTML = rowStr;
	
	
	
	
	
	
	
}catch(e){
	
		
		console.log(e);
		alert(e.response.data.errorMessage);
	
	
	
}
	
	
	
	
	
	
}





//---------------------------------------------------



//view all request of specific employeee



async function viewEmployeeRequests (){
	
	//serve the select element
	//i am lazy so i will hard code the employees instead of using the loop
	
	
			let container= 	domeSelectores().container;
								
								container.innerHTML= " ";
								container.innerHTML=  `
								
								
	
	<div class="form-row align-items-baseline">
	

 		<div class="col-sm-12 ">
 			
 				<select class="custom-select view-employee-request" id="inlineFormCustomSelectPref">
    			<option selected>Employee</option>
   				 <option value="26">Suntran123</option>
   				 <option value="23">MoonTran2157</option>
   				
 			 </select>
  
 			
 		</div>
 			
		<div class="col-sm-12  d-flex justify-content-center ">
 			
 			<button type="button" class="btn btn-primary btn-sm   btn-default text-right  "  id = "view-employee-request-btn">View</button>
  
 			
 		</div>


								
								
								
								` ;
							
	
	 document.getElementById('view-employee-request-btn').addEventListener('click', async function(){
		
		let employeeSelect = document.getElementsByClassName('view-employee-request')[0];
		
		
		if ( employeeSelect.value == "Employee" ){
			
			alert('please select employee');
			return;
		}
		
		
		//get id of an  employee
		
		
		let id = employeeSelect.value;
		//send request to get all request of an employee
					let res =  await axios({
   method: 'post',
  url: 'http://localhost:8080/RepayHub/api/manager/view/allRequestOfAnEmployee',
  data:{id : id}
		
		
		
	});


	 populateTable(res.data);
//	console.log(res.data)
	
	});


}








//--------------------------------------------------

 
 //add all event click to btns
 function addEventsListeners(){
	let approveBtn =  domeSelectores().approveBtn;
	 approveBtn.addEventListener("click",approveRequest );
	let denyBtn =  domeSelectores().denyBtn;
		 denyBtn.addEventListener("click",denyRequest );
		 
	let viewResolvedRequestBtn = domeSelectores().viewResolvedBtn;
	 viewResolvedRequestBtn.addEventListener('click', viewResolvedRequest)
	 
	 
	 let viewAllemployeeBtn = domeSelectores().viewEmployeeInfoBtn;
	 
	 
	 viewAllemployeeBtn.addEventListener('click', viewAllemployee);
	 
	 
	 let viewEmployeeRequestBtn = domeSelectores().viewEmployeeRequestBtn;
	 
	 
	 viewEmployeeRequestBtn.addEventListener('click',viewEmployeeRequests );
	 
};
 
 
 
 addEventsListeners();
 
 
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

        
 