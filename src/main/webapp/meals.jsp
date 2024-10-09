<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <title>Meals</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<button onclick="window.location.href='meals?action=add'">Add meal</button>
<br>
<table border="1">
    <tr>

        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
        <th>Delete</th>
        <th>Update</th>
    </tr>
    <c:forEach var="meal" items="${meals}">

            <tr style="color:${!(meal.isExcess()) ? 'green' : 'red'}">

                <td>${DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(meal.getDateTime())}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td>
                    <button onclick="window.location.href='meals?id=${meal.getId()}&action=delete'">Delete</button>
                </td>
                <td>
                    <button onclick="window.location.href='meals?id=${meal.getId()}&action=update&date=${meal.getDateTime()}&description=${meal.getDescription()}&calories=${meal.getCalories()}'">
                        Update
                    </button>
                </td>
            </tr>
    </c:forEach>
</table>
</body>
</html>
