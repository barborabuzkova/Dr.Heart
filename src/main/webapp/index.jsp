<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dr.Heart</title> <%-- website title --%>
</head>
<body>
<h1>Welcome to Dr. Heart!</h1>
<br/>
<a href="https://forms.office.com/r/s8WjjFmgzg" target="_blank">Take the test!</a>
<br/>
<% String message = (String) request.getServletContext().getAttribute("message");%>
<%= message == null ? "" : message %>
<br/>
<form action="hello-servlet" method="GET">
    <label for="emailProvided">School Email:</label>
    <input type="email" id="emailProvided" name="emailProvided"> <br>
    <input type="submit" value="Submit">
</form>
</body>
</html>