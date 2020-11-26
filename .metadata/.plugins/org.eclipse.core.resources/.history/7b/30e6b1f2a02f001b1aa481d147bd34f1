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
		    
		  
		  </tr>
		  <%}} %>
		  
		</tbody>
		</table>
	</div>
</body>
</html>