<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "trainstation.model.Station" %>
<%@page import= "trainstation.model.TrainSchedule" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Station Schedule</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<a href= "<%= request.getContextPath() %>/rep" >Questions</a>
	<div>
		<% ArrayList<Station> stations = (ArrayList<Station>) request.getAttribute("stations"); %>
		<form action="<%= request.getContextPath() %>/rep" method="get">
			<label for="stations">Station</label>
			<input type="hidden" name="schedule" value="station" ></input>
			<select name="stationId" id="stations">
				<% for(Station station : stations) { %>
					<option value= <%= station.getStationId() %> > <%= station.getStationName() %> </option>
				<% } %>
			</select>
			<button>Search</button>
		</form>
	</div>
	<% ArrayList<TrainSchedule> schedule = (ArrayList<TrainSchedule>) request.getAttribute("schedule"); %>
	<% if(schedule == null) schedule = new ArrayList<TrainSchedule>(); %>
	<table class="table table-hover">
		<thead>
		  <tr>
		    <th scope="col">TrainID</th>
		    <th scope="col">StationID</th>
		    <th scope="col">Arrival Time</th>
		    <th scope="col">Depart Time</th>
		  </tr>
		</thead>
		<tbody>
		  <% for (TrainSchedule train: schedule){%>
		   <tr>
		    <th scope="row"><%= train.gettrainId() %></th>
		    <td><%= train.getstationId() %></td>
		    <td><%= train.getArrivalTime() %></td>
		    <td><%= train.getDepartTime() %></td>
		  </tr>
		  <%} %>
		  
		</tbody>
</table>
</body>
</html>