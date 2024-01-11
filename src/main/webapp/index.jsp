<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dr.Heart</title> <%-- website title --%>
</head>
<body style="background-color: lavender">
<div style="text-align: center;">
<h1>Welcome to Dr. Heart!</h1>
<br/>
<a href="https://forms.office.com/r/s8WjjFmgzg" target="_blank">Don't have an account yet? Take the test!</a>
<br/>
<% String message = (String) request.getServletContext().getAttribute("message");%>
<%= message == null ? "" : message %>
<br/>
Already have an account? Log in here:
<br>
<form action="MatchPage" method="GET">
    <label for="emailProvided">School Email:</label>
    <input type="email" id="emailProvided" name="emailProvided"> <br>
    <input type="submit" value="Submit">
</form>
</div>
</body>
</html>