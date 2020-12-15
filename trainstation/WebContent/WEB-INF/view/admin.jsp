<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "trainstation.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script type="text/javascript">
	function del(element){
		console.log(element.children[1].innerHTML);
		$.ajax({
		    url: '/trainstation/admin?' + $.param({
		    	"userName": element.children[1].innerHTML
		    }),
		    type: 'DELETE',
		    success: function(result) {console.log(result);}
		});
	}
</script>
<style>
.come-out {
transform: translateX(-100px);
transition: 0.5s;
opacity: 0;
}
.come-in{
transform: translateX(0px);
transition: 0.5s;
opacity: 1;
}
</style>
</head>
<body>

<div class="container-fluid">
<div class="row">
<div class="col-2">
<div class="list-group bg-danger shadow-lg" style="min-height: 100vh; position: fixed; width: 15vw">
  <a href="<%= request.getContextPath() %>/admin" class="bg-danger fw-bold list-group-item list-group-item-action"></i>Home</a>
  <a href="<%= request.getContextPath() %>/admin_reservations" class="bg-danger fw-bold list-group-item list-group-item-action">Reservation</a>
  <a href="<%= request.getContextPath() %>/admin_profit"  class="bg-danger fw-bold list-group-item list-group-item-action">Profits</a>
  <a href="#" class="bg-danger fw-bold list-group-item list-group-item-action">Customers</a>
  <a href="<%= request.getContextPath() %>/admin" class="bg-danger fw-bold list-group-item list-group-item-action">Employees</a>
  <form action="<%= request.getContextPath() %>/logout">
    	<button type="submit" value="Logout">Logout</button>
  </form>
</div>
</div>
<div class="col-9">
<% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users"); %>
<table class="table table-hover">
<caption><h2>List of employees</h2></caption>
<thead>
  <tr>
    <th scope="col">Username</th>
    <th scope="col">First Name</th>
    <th scope="col">Last Name</th>
    <th scope="col">Password</th>
    <th scope="col">SSN</th>
    <th scope="col">email</th>
    <th scope="col">Role</th>
  </tr>
</thead>
<tbody>
  <% for (User user: users){%>
   <tr id=<%= user.getUsername() %>>
    <th scope="row"><%= user.getUsername() %></th>
    <td><%= user.getFirstName() %></td>
    <td><%= user.getLastName() %></td>
    <td><%= user.getPassword() %></td>
    <td><%= user.getSSN() %></td>
    <td><%= user.getEmail() %></td>
    <td><%= user.getUserRole()%></td>
    <td>
    	<form action="<%= request.getContextPath() %>/admin" method="post">
    		<input type = "hidden" name="edit" value="2" />
    		<input type = "hidden" name="userName" value=<%= user.getUsername() %> />
    		<input type = "hidden" name="firstName" value=<%= user.getFirstName() %> />
    		<input type = "hidden" name="lastName" value=<%= user.getLastName() %> />
    		<input type = "hidden" name="password" value=<%= user.getPassword() %> />
    		<input type = "hidden" name="SSN" value=<%= user.getSSN() %> />
    		<input type = "hidden" name="email" value=<%= user.getEmail() %> />
    		<button class="btn btn-primary">Edit</button>
    	</form>
    	
    	<form action="<%= request.getContextPath() %>/admin" method="post">
    		<input type = "hidden" name = "edit" value="0" />
    		<input type = "hidden" name = "userName" value=<%= user.getUsername() %> />
    		<button class="btn btn-danger" type="submit">Delete</button>
    	</form>
    </td>
  
  </tr>
  <%} %>
  
</tbody>
</table>
<button id="newEmp" class="btn btn-warning">Add New Employee</button>
<form id="newEmpForm" action="<%= request.getContextPath() %>/admin" method="post" style="width: 500px;" class="register-form invisible come-out">
		<input type = "hidden" name="edit" value="1" />
		<div class="form-group" align="left">
			<label for="username">UserName</label>
			<input type="text" id="username" name="userName" placeholder="Username" class="form-control" required="required"/>
		</div>
		<div class="form-group" align="left">
			<label for="first-name">First Name</label>
			<input type="text" id="first-name" name="firstName" placeholder="First Name" class="form-control" required="required"/>
		</div>
		<div class="form-group" align="left">
			<label for="last-name">Last Name</label>
			<input type="text" id="last-name" name="lastName" placeholder="Last Name" class="form-control" required="required"></td>
		</div>
		<div class="form-group" align="left">
			<label for="password">Password</label>
			<input type="password" id="password" name="password" placeholder="Password" class="form-control" required="required"/>
		</div>
		<div class="form-group" align="left">
			<label for="ssn">SSN</label>
			<input type="text" id="ssn" name="SSN" placeholder="SSN" class="form-control" required="required"/>
		</div>
		<div class="form-group" align="left">
			<label id="email">Email</label>
			<input type="text" id="email" name="email" placeholder="Email" class="form-control" required="required"/>
		</div>
		<button type="submit" class="btn btn-primary submit">Submit</button>
	</form>
	</div>
	</div>
</div>
<script>
	const btnNewEmp = document.getElementById("newEmp");
	const formNewEmp = document.getElementById("newEmpForm");
	btnNewEmp.addEventListener('click', function(e){
		formNewEmp.classList.toggle('invisible');
		formNewEmp.classList.toggle('come-in');
	})
</script>
</body>
</html>