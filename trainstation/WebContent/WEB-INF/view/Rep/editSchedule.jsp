<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "trainstation.model.Station" %>
<%@page import= "trainstation.model.TrainSchedule" %>
<%@page import= "trainstation.model.TrainRoute" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Schedule</title>
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
		<form action="<%= request.getContextPath() %>/rep" method="get">
			<label for="trainId">TrainID</label>
			<input type="hidden" name="schedule" value="edit" ></input>
			<select name="trainId" id="trainId">
				<% for(String id : trains) { %>
					<option value= <%= id %> > <%= id %> </option>
				<% } %>
			</select>
			<button>Search</button>
		</form>
	</div>
	<% TrainRoute scheduleInfo = (TrainRoute) request.getAttribute("scheduleInfo"); %>
	<table class="table table-hover">
		<thead>
		  <tr>
		    <th scope="col">TrainID</th>
		    <th scope="col">Fare</th>
		    <th scope="col">Line Name</th>
		    <th scope="col">Origin</th>
		    <th scope="col">Destination</th>
		    <th scope="col">Origin Time</th>
		    <th scope="col">Destination Time</th>
		  </tr>
		</thead>
		<tbody>
			<% if(scheduleInfo != null) { %>
			<form action="<%= request.getContextPath() %>/rep" method="post">
				<input type="hidden" name="schedule" value="edit" ></input>
				<input type="hidden" name="action" value="info" ></input>
			  <input type="hidden" name="trainId" value= <%= scheduleInfo.gettrainId() %> ></input>
			  <th scope="row"><%= scheduleInfo.gettrainId() %></th>
			  <td> <input type="number" name="fare" value= <%= scheduleInfo.getTotalFare() %> required></input></td>
			  <td> <input type="text" name="lineName" value= "<%= scheduleInfo.getLineName() %>" required></input></td>
			  <td> <input type="text" name="origin" value= <%= scheduleInfo.getOrigin() %> required></input></td>
			  <td> <input type="text" name="destination" value= <%= scheduleInfo.getDestination() %> required></input></td>
			  <td> <input type="time" name="originTime" value= <%= scheduleInfo.getArrivalTime() %> required></input> </td>
			  <td> <input type="time" name="destinationTime" value= <%= scheduleInfo.getDepartTime() %> required></input> </td>
			  <td> <button>Edit</button> </td>
			</form>
			<% } %>
		</tbody>
	</table>
	
	
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
			  <form action="<%= request.getContextPath() %>/rep" method="post">
			  	<input type="hidden" name="schedule" value="edit" ></input>
			  	<input type="hidden" name="trainId" value= <%= train.gettrainId() %> ></input>
			  	<input type="hidden" name="oldStationId" value= <%= train.getstationId() %> ></input>
			  	<input type="hidden" name="oldArrivalTime" value= <%= train.getArrivalTime() %> ></input>
			    <th scope="row"><%= train.gettrainId() %></th>
			    <td> <input type="text" name="newStationId" value= <%= train.getstationId() %> ></input></td>
			    <td> <input type="time" id="newArrivalTime" name="newArrivalTime" value= <%= train.getArrivalTime() %> required></input> </td>
			    <td> <input type="time" id="departTime" name="departTime" value= <%= train.getDepartTime() %> required></input> </td>
			    <td><button>Edit</button></td>
		  	</form>
		  	<form action="<%= request.getContextPath() %>/rep" method="post">
		  		<input type="hidden" name="schedule" value="edit" ></input>
		  		<input type="hidden" name="action" value="delete" ></input>
		  		<input type="hidden" name="trainId" value= <%= train.gettrainId() %> ></input>
			  	<input type="hidden" name="oldStationId" value= <%= train.getstationId() %> ></input>
			  	<input type="hidden" name="oldArrivalTime" value= <%= train.getArrivalTime() %> ></input>
		  		<td><button>Delete</button></td>
		  	</form action="<%= request.getContextPath() %>/rep" method="post">
				</tr>
		  <%} %>
		  <% if (schedule.size() > 0) { %>
		  	<% TrainSchedule train = schedule.get(0); %>
			  <tr>
				  <form action="<%= request.getContextPath() %>/rep" method="post">
				  	<input type="hidden" name="schedule" value="edit" ></input>
				  	<input type="hidden" name="action" value="add" ></input>
				  	<input type="hidden" name="trainId" value= <%= train.gettrainId()  %> ></input>
				    <th scope="row"><%= train.gettrainId() %></th>
				    <td> <input type="text" name="newStationId" required ></input></td>
				    <td> <input type="time" id="newArrivalTime" name="newArrivalTime" required></input> </td>
				    <td> <input type="time" id="departTime" name="departTime" required> </td>
				    <td><button>Add</button></td>
			  	</form>
				</tr>
			<% } %>
		</tbody>
</table>
</body>
</html>