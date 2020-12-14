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
	<form action="<%= request.getContextPath() %>/question" method="get">
		<input type='hidden' name='ask' value='1'></input>
		<button type="submit">Ask a question</button>
	</form>
	<form action="<%= request.getContextPath() %>/question" method="get">
		<input type='text' name='keywords'></input>
		<button type="submit">Search</button>
	</form>
	<div>
		<% ArrayList<Question> questions = (ArrayList<Question>) request.getAttribute("questions"); %>
		<% for(Question question : questions) { %>
			<div id=<%= question.getQid() %>>
				<p><%= question.getQuestion() %></p>
				<p>Asked by: <%= question.getCustid() %> </p>
				<% if (question.getAnswer() == null) { %>
					<p>Unanswered</p>
				<% } else { %>
					<p><%= question.getAnswer() %></p>
					<p>Answered by: <%= question.getRepid() %></p>
				<% } %>
			</div>
		<% } %>
	</div>
</body>
</html>