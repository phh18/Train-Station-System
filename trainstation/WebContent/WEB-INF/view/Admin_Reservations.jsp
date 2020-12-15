<%@page import="trainstation.model.Reservation"%>
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
</head>
<body>

<div class="container-fluid">
	<div class="row">
		<div class="col-2">
		<div class="list-group bg-danger" style="min-height: 100vh; position: fixed; width: 15vw">
		  <a href="<%= request.getContextPath() %>/admin" class="bg-danger fw-bold list-group-item list-group-item-action"></i>Home</a>
  		  <a href="<%= request.getContextPath() %>/admin_reservations" class="bg-danger fw-bold list-group-item list-group-item-action">Reservation</a>
          <a href="<%= request.getContextPath() %>/admin_profit" class="bg-danger fw-bold list-group-item list-group-item-action">Profits</a>
          <a href="#" class="bg-danger fw-bold list-group-item list-group-item-action">Customers</a>
  		  <a href="<%= request.getContextPath() %>/admin" class="bg-danger fw-bold list-group-item list-group-item-action">Employees</a>
		
		</div>
		</div>
		<div class="col-9">
		<div class="d-flex justify-content-between m-5" style="width: 50vw;">
		<form class="d-flex justify-content-around align-items-center" style="width: 50%" action="<%= request.getContextPath() %>/admin_reservations" method="post">
		<label>Search by Transit Line</label>
		<select name="transitLine" class="form-select" aria-label="Default select example">
		  <option value="Main-Bergen County">Main-Bergen County</option>
		  <option value="Montclair-Boonton">Montclair-Boonton</option>
		  <option value="Northeast Corridor">Northeast Corridor</option>
		  <option value="Atlantic City">Atlantic City</option>
		  <option value="New Brunswick-Paters">New Brunswick-Paters</option>
		</select>
		<button type="submit" class="btn btn-primary">Search</button>
		</form>
		
		<form class="d-flex justify-content-around align-items-center" style="width: 50%" action="<%= request.getContextPath() %>/admin_reservations" method="post">
		<label for="fname">Search by Username</label>
		  <input type="text" name="userName"><br>
		<button type="submit" class="btn btn-primary">Search</button>
		</form>
		</div>
		<% ArrayList<Reservation> reservations = (ArrayList<Reservation>) request.getAttribute("reservations"); %>
		
		<table class="table table-hover">
		<caption>List of Reservation</caption>
		<thead>
		  <tr>
		    <th scope="col">ReservationId</th>
		    <th scope="col">Transit Line</th>
		    <th scope="col">Origin</th>
		    <th scope="col">Destination</th>
		    <th scope="col">Trip Type</th>
		    <th scope="col">Travel Date</th>
		    <th scope="col">Origin Time</th>
		    <th scope="col">Destination Time</th>
		    <th scope="col">Fare</th>
		    <th scope="col">Username</th>
		    <th scope="col">Ticket Type</th>
		  </tr>
		</thead>
		<tbody>
		<%int totalFare = 0; %>
		  <% 
		  if (reservations !=null){
		  for (Reservation re: reservations){%>
		  <%totalFare += re.getFare(); %>
		   <tr>
		    <th scope="row"><%= re.getReservationId() %></th>
		    <td><%= re.gettrainId() %></td>
		    <td><%= re.getOrigin()%></td>
		    <td><%= re.getDestination() %></td>
		    <td><%= re.getTripType() %></td>
		    <td><%= re.getTravelDate() %></td>
		    <td><%= re.getOriginTime()%></td>
		    <td><%= re.getDestinationTime()%></td>
		    <td>$<%= re.getFare()%></td>
		    <td><%= re.getUsername() %></td>
		    <td><%= re.getTicketType()%></td>
		  
		  </tr>
		  <%}} %>
		  <h1>Total Spent: $<%= totalFare %></h1>
		  
		</tbody>
		</table>
		
		</div>
	</div>
</div>

</body>
</html>