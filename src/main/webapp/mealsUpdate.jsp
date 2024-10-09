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
    <input type="number" name="id" value="${meal.id}">
  </label>
  <br>
  <label>Name:
    <input type="datetime-local" name="date" value="${meal.dateTime}">
  </label>
  <br>
  <br>
  <label>Description:
    <input type="text" name="description" value="${meal.description}">
  </label>
  <br>
  <br>
  <label>Calories:
    <input type="number" min="0" name="calories" value="${meal.calories}">
  </label>
  <br>
  <button type="submit">SEND</button>
</form>
</body>
</html>