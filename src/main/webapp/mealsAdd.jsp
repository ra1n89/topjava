<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>mealAdd</title>
</head>
<body>
Adding meal
<br>
<br>
<form  action="meals?action=add" method="post">
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

