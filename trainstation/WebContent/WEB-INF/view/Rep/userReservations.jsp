<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "trainstation.model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Reservation</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<a href= "<%= request.getContextPath() %>/rep" >Questions</a>
	<div>
		<% ArrayList<String> trains = (ArrayList<String>) request.getAttribute("trains"); %>
		<% String lineName = request.getParameter("lineName"); %>
		<% if(lineName == null) lineName = ""; %>
		<form action="<%= request.getContextPath() %>/rep" method="get">
			<input type="hidden" name="user" value="rev" ></input>
			<label for="lineName">Line Name</label>
			<input type="text" name="lineName" id="lineName" value= "<%= lineName %>" required/> 
			<label for="date">Date</label>
			<input type="date" name="date" id="date" value= <%= request.getParameter("date") %> required/> 
			<button>Search</button>
		</form>
	</div>
	<% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users"); %>
	<% if(users == null) users = new ArrayList(); %>
<table class="table table-hover">
<thead>
  <tr>
    <th scope="col">Username</th>
    <th scope="col">First Name</th>
    <th scope="col">Last Name</th>
    <th scope="col">Email</th>
  </tr>
</thead>
<tbody>
  <% for (User user: users){%>
   <tr id=<%= user.getUsername() %>>
    <th scope="row"><%= user.getUsername() %></th>
    <td><%= user.getFirstName() %></td>
    <td><%= user.getLastName() %></td>
    <td><%= user.getEmail() %></td>
  </tr>
  <%} %>
  
</tbody>
</table>
</body>
</html>