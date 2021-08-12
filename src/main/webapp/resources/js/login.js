/**
 * 
 */

 
 
 let form = document.getElementById("login").addEventListener('submit', login);

async function login(e){
	e.preventDefault();
	let username = document.getElementById("username").value;
	let password = document.getElementById("password").value;

	let user = {
		username,
		password
	}

	console.log(user);


	try{
		
		
		/*
			let res = await fetch('http://localhost:8080/RepayHub/api/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(user)
		});
		
		
		
				res = await  res.json();
				
		*/	
		
		
	
alert("Please wait, Hibernate takes forever");
 let res =  await axios({
   method: 'post',
  url: 'http://localhost:8080/RepayHub/api/login',
  data:  user ,
  
 });


		console.log(res.data.username);

	
	console.log('go to homepage')
	
	location.href = 'http://localhost:8080/RepayHub/homepage';
	} catch(e){
	
	
	
	console.log(e.response.data.errorMessage);
		
		alert(e.response.data.errorMessage);
		return;
	}



	}
	
	/*

	 function populateHomePage(username) {
    //Use a simple enhanced for of loop
    for (postObj of posts) {
        //Create a new div to store our post object in the html document
        let post = document.createElement('div');
        post.innerHTML = `<h2>${postObj.title}</h2>
                      <p>${postObj.body}</p>`;
        //console.log(post);
        //To add these posts to the page we will use append
        container.append(post);
    }
}


 //location.href = '/RepayHub/api/session';
 
 
 */