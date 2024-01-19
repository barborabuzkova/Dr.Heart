<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dr.Heart</title> <%-- website title --%>
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="general-body">
<h1 class="heading">Welcome to Dr. Heart!</h1>
<br>
<a class="link" href="https://forms.office.com/r/s8WjjFmgzg" target="_blank">Don't have an account yet? Take the test!</a>
<br>
<% String message = (String) request.getServletContext().getAttribute("message");%>
<%= message == null ? "" : message %>
<br>
Already have an account? Log in here:
<br>
<form action="MatchPage" method="GET">
    <label class="general-body" for="emailProvided">School Email:</label>
    <input class="input-box" type="email" id="emailProvided" name="emailProvided">
    <br>
    <input class="submit" type="submit" value="Log in">
</form>
</body>
</html>