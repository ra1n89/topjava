<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>mealUpdate</title>
</head>
<body>
Adding meal
<br>
<br>
<form action="meals?action=update" method="post">
  <label hidden="hidden">ID:
    <input type="number" name="id" value="${id}">
  </label>
  <br>
  <label>NAME:
    <input type="datetime-local" name="date" value="${date}">
  </label>
  <br>
  <br>
  <label>DESCRIPTION:
    <input type="text" name="description" value="${description}">
  </label>
  <br>
  <br>
  <label>CALORIES:
    <input type="number" min="0" name="calories" value="${calories}">
  </label>
  <br>
  <button type="submit">SEND</button>
</form>
</body>
</html>