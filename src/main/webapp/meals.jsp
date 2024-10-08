<%--
  Created by IntelliJ IDEA.
  User: prora
  Date: 07.10.2024
  Time: 23:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.time.format.DateTimeFormatter" %>

<html>
<head>
    <title>Meals</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
    <table border="1">
        <tr>
            <th>DATE</th>
            <th>DESCRIPTION</th>
            <th>Calories</th>
        </tr>
        <c:forEach var="meal" items="${meals}">
            <c:if test = "${meal.isExcess()==false}">
        <tr style="color:green">

                <td>${DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(meal.getDateTime())}</td>
                <td>${meal.getDescription()}</td>
                <td>${meal.getCalories()}</td>
                <td>${meal.isExcess()}</td>
        </tr>
            </c:if>
            <c:if test = "${meal.isExcess()==true}">
                <tr style="color:red">

                    <td>${DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(meal.getDateTime())}</td>
                    <td>${meal.getDescription()}</td>
                    <td>${meal.getCalories()}</td>
                    <td>${meal.isExcess()}</td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
</body>
</html>
