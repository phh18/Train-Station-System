<%@page import="trainstation.model.Profit"%>
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
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
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
  <a href="#" class="bg-danger fw-bold list-group-item list-group-item-action"></i>Home</a>
  <a href="<%= request.getContextPath() %>/admin_reservations" class="bg-danger fw-bold list-group-item list-group-item-action">Reservation</a>
  <a href="#" class="bg-danger fw-bold list-group-item list-group-item-action">Profits</a>
  <a href="#" class="bg-danger fw-bold list-group-item list-group-item-action">Customers</a>
  <a href="<%= request.getContextPath() %>/admin" class="bg-danger fw-bold list-group-item list-group-item-action">Employees</a>

</div>
</div>
<div class="col-9">
<canvas id="myChart"></canvas>
<% ArrayList<Profit> profitByUsername  = (ArrayList<Profit>) request.getAttribute("profitByUsername"); %>
<% ArrayList<Profit> profitByTransitLine = (ArrayList<Profit>) request.getAttribute("profitByTransitLine"); %>
<% ArrayList<Profit> mostActiveLines = (ArrayList<Profit>) request.getAttribute("mostActivelines"); %>
		
		<table class="table table-hover">
		<caption><h2>List of Profit by Customers</h2></caption>
		<thead>
		  <tr>
		    <th scope="col">Username</th>
		    <th scope="col">Profit</th>
		    <th scope="col">Avatar</th>
		  </tr>
		</thead>
		<tbody>
	
		  <% 
		  if ( profitByUsername!=null){
		  for (Profit pro: profitByUsername){%>
		  <%if (profitByUsername.indexOf(pro) ==0) {%>
		   <tr>
		    <th scope="row"><%= pro.getTitle() %><span class="badge badge-danger">Best Customer</span></th>
		    <td>$<%= pro.getProfit() %></td>
		    <td style="width: 33%">
		    <img style="width: 25%; height: 10%;" src="https://upload.wikimedia.org/wikipedia/commons/8/8c/Cristiano_Ronaldo_2018.jpg">
		    </td><%
		    
		  continue;} %>
		
		  
		   <tr>
		    <th scope="row"><%= pro.getTitle() %></th>
		    <td>$<%= pro.getProfit() %></td>
		    <td style="width: 33%">
		    <img style="width: 25%; height: 10%;" src="https://upload.wikimedia.org/wikipedia/commons/8/8c/Cristiano_Ronaldo_2018.jpg">
		    </td>
		   </tr>
		   <%}} %>
		</tbody>
		</table>
		<table class="table table-hover">
		<caption><h2>List of Profit by Train Line</h2></caption>
		<thead>
		  <tr>
		    <th scope="col">Train Line</th>
		    <th scope="col">Profit</th>
		    <th scope="col">Image</th>
		  </tr>
		</thead>
		<tbody>
	
		  <% 
		  if (profitByTransitLine!=null){
		  for (Profit pro: profitByTransitLine){%>
		   <tr>
		    <th scope="row"><%= pro.getTitle() %></th>
		    <td>$<%= pro.getProfit() %></td>
		    <td style="width: 33%">
		    <img style="width: 25%; height: 10%;" src="https://media.kasperskydaily.com/wp-content/uploads/sites/92/2015/12/06023356/train-hack-featured-1.jpg">
		    </td>
		 
		   
		  </tr>
		  <%}} %>
		</tbody>
		</table>
		<table class="table table-hover">
		<caption><h2>5 Most Active lines</h2></caption>
		<thead>
		  <tr>
		    <th scope="col">Train Line</th>
		    <th scope="col">Number of Reservations</th>
		    <th scope="col">Image</th>
		  </tr>
		</thead>
		<tbody>
	
		  <% 
		  if ( mostActiveLines!=null){
		  for (Profit pro: mostActiveLines){%>
		  <%if (mostActiveLines.indexOf(pro) ==0) {%>
		   <tr>
		    <th scope="row"><%= pro.getTitle() %><span class="badge badge-danger">Best Line</span></th>
		    <td><%= pro.getProfit() %></td>
		    <td style="width: 33%">
		    <img style="width: 25%; height: 10%;" src="https://www.adirondack.net/images/trainridefall1.jpg">
		    </td><%
		    
		  continue;} %>
		
		  
		   <tr>
		    <th scope="row"><%= pro.getTitle() %></th>
		    <td><%= pro.getProfit() %></td>
		    <td style="width: 33%">
		    <img style="width: 25%; height: 10%;" src="https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/gettyimages-102728664-1599177391.jpg?crop=1.00xw:0.623xh;0,0.218xh&resize=1200:*">
		    </td>
		   </tr>
		   <%}} %>
		</tbody>
		</table>
</div>
</div>
</div>
<script>
let labels = [];
let data = [];
<% ArrayList<Profit> salesPerMonth  = (ArrayList<Profit>) request.getAttribute("salesPerMonth"); %>
<% 
if (salesPerMonth!=null){
for (Profit pro: salesPerMonth){%>
  labels.push('<%= pro.getTitle()%>');
  data.push(<%= pro.getProfit()%>);
<%}} %>

let ctx = document.getElementById('myChart').getContext('2d');
let chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'line',
   

    // The data for our dataset
    data: {
        labels: labels,
        datasets: [{
            label: 'Profit Per Month',
            backgroundColor: 'rgb(255, 99, 132)',
            borderColor: 'rgb(255, 99, 132)',
            data: data
        }]
    },
 
    // Configuration options go here
     options: {
        scales: {
            yAxes: [{
                ticks: {
                    // Include a dollar sign in the ticks
                    callback: function(value, index, values) {
                        return '$' + value;
                    }
                }
           
            }]
        }
    }
});

</script>

</body>
</html>