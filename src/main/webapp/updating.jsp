<%--
  Created by IntelliJ IDEA.
  User: prora
  Date: 08.10.2024
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>AddMeal</title>
</head>
<body>
Adding meal
<br>
<br>
<form action="updating" method="post">
  <label>ID:
    <input type="number" name="id" value="${id}">
  </label>
  <br>
  <label>NAME:
    <input type="datetime-local" name="date">
  </label>
  <br>
  <br>
  <label>DESCRIPTION:
    <input type="text" name="description">
  </label>
  <br>
  <br>
  <label>CALORIES:
    <input type="number" min="0" name="calories">
  </label>
  <br>
  <button type="submit">SEND</button>
</form>
</body>
</html>