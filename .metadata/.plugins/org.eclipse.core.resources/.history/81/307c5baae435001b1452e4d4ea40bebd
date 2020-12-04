<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "trainstation.model.TrainSchedule" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<div>
		<% ArrayList<TrainSchedule> schedules = (ArrayList<TrainSchedule>) request.getAttribute("schedule"); %>
		<table class="table table-hover">
		<caption>List of users</caption>
		<thead>
		  <tr>
		    <th scope="col">TrainID</th>
		    <th scope="col">StationID</th>
		    <th scope="col">Arrival Time</th>
		    <th scope="col">Depart Time</th>
		    <th scope="col">Fare</th>
		  </tr>
		</thead>
		<tbody>
		  <% 
		  if (schedules !=null){
		  for (TrainSchedule schedule: schedules){%>
		   <tr>
		    <th scope="row"><%= schedule.gettrainId() %></th>
		    <td><%= schedule.getstationId() %></td>
		    <td><%= schedule.getArrivalTime() %></td>
		    <td><%= schedule.getDepartTime() %></td>
		    <td>$<%= schedule.getFare() %></td>
		  
		  </tr>
		  <%}} %>
		  
		</tbody>
		</table>
	</div>
</body>
</html>