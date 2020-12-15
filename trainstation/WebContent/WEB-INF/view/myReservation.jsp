<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "java.util.Date"%>
<%@page import= "java.text.SimpleDateFormat"%>
<%@page import= "trainstation.model.Reservation" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reservation Success</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>

<div>${message}</div>

<div>
		<% ArrayList<Reservation> myReservation = (ArrayList<Reservation>) request.getAttribute("myReservation"); %>
		<table class="table table-hover">
		<thead>
		  <tr>
		  	<th scope="col">ReservationID</th>
		    <th scope="col">TrainID</th>
		    <th scope="col">Origin</th>
		    <th scope="col">Destination</th>
		    <th scope="col">Trip Type</th>
		    <th scope="col">Travel Date</th>
		    <th scope="col">Origin Time</th>
		    <th scope="col">Destination Time</th>
		    <th scope="col">Fare</th>
		    <th scope="col">Ticket Type</th>
		  </tr>
		</thead>
		<tbody>
		  <% 
		  SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		  Date date = new Date(System.currentTimeMillis());
		  String now = formatter.format(date);
		  System.out.println(now);
		  
		  if (myReservation !=null){
		  for (Reservation myRes: myReservation){%>
		  <form action="<%= request.getContextPath() %>/reserve" method="post" class="register-form">
		   <tr>
		   	<th scope="row"><select name="reservationId"><option value="<%= myRes.getReservationId() %>"><%= myRes.getReservationId() %></option></select></th>
		    <td><%= myRes.gettrainId() %></td>
		    <td><%= myRes.getOrigin() %></td>
		    <td><%= myRes.getDestination() %></td>
		    <td><%= myRes.getTripType() %></td>
		    <td><%= myRes.getTravelDate() %></td>
		  	<td><%= myRes.getOriginTime() %></td>
		  	<td><%= myRes.getDestinationTime() %></td>
		  	<td><%= myRes.getFare() %></td>
		  	<td><%= myRes.getTicketType() %></td>
		  	<%if (myRes.getTravelDate().compareTo(now)>=0){%>
		  	<td><button type="submit" class="btn btn-danger">Cancel Reservation</button></td>
		  	<%} else{ %>
		  	<td><button type="button" disabled class="btn btn-warning">Past Reservation</button></td>
		  	<%} %>
		  	
		  </form>
		  </tr>
		  <%}} %>

		</tbody>
		</table>
	</div>
	
	 <div><button><a href="<%= request.getContextPath() %>/login">Back to Main Page</a></button></div>

</body>
</html>