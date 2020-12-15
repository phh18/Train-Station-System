<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import= "java.util.ArrayList"%>
<%@page import= "trainstation.model.Question" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Question</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.2.1.min.js">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body>
	<a href= "<%= request.getContextPath() %>/rep?schedule=station">Station Schedule</a>
	<a href= "<%= request.getContextPath() %>/rep?schedule=edit">Edit Schedule</a>
	<form action="<%= request.getContextPath() %>/rep" method="get">
		<input type='text' name='keywords'></input>
		<button type="submit">Search</button>
	</form>
	<div>
		<% ArrayList<Question> questions = (ArrayList<Question>) request.getAttribute("questions"); %>
		<% for(Question question : questions) { %>
			<div>
				<p><%= question.getQuestion() %></p>
				<p>Asked by: <%= question.getCustid() %> </p>
				<% if (question.getAnswer() == null) { %>
					<form action="<%= request.getContextPath() %>/rep" method="post">
						<input type= "hidden" name= "ans" value="0"></input>
						<input type= "hidden" name= "qid" value= <%= question.getQid() %>></input>
						<button>Answer</button>
					</form>
				<% } else { %>
					<p><%= question.getAnswer() %></p>
					<p>Answered by: <%= question.getRepid() %></p>
				<% } %>
			</div>
		<% } %>
	</div>
</body>
</html>