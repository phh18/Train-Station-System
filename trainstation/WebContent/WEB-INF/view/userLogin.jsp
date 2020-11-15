<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="http://localhost:8000/trainstation/assets/css/styles.css" rel="stylesheet"/>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<style>
body, html {
  height: 100%;
}
body{
	background-color: rgb(51, 51, 51);
	display: flex;
	flex-direction: column;
	justify-content: center;
}
.main {
	background-color: white;
	padding: 5em 2em 1em 2em;
	width: 30em;
	max-width: 100%;
	margin: auto;
	border-radius: 2%;
}
.submit{
	margin: 1em 0 1em 0;
	width: 100%;
}
.link{
	padding: 5em 0 0 0;
}
</style>
</head>
<body>

<div class="main" align="center">
	<h3>Login</h3>
	<form action="<%= request.getContextPath() %>/login" method="post" class="register-form">
		<div>
			${message}
		</div>
		<div class="form-group" align="left">
			<label for="userName" >UserName</label>
			<input type="text" id="username" name="userName" class="form-control" placeholder="Username" required="required"/>
		</div>
		<div class="form-group" align="left">
			<label for="password">Password</label>
			<input type="password" id="password" name="password" class="form-control" placeholder="Password" required="required"/>
		</div>
		<button type="submit" class="btn btn-primary submit">Submit</button>
	 </form>
	 
	<div class="link">
		Don't have an account? <a href="/trainstation/register">Sign up</a>
	</div>
</div>

</body>
</html>