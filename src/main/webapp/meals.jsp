<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="f" uri="http://example.com/functions" %>
<html lang="ru">
<head>
    <title>Meals</title>
    <link rel="stylesheet" href="mystile.css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>Date</th>
        <th>Description</th>
        <th>Calories</th>
    </tr>
    <c:forEach var="mealTo" items="${mealToList}">
        <tr class=${mealTo.excess == 'true'? 'red' : 'green'}>
            <td><c:out value="${f:formatLocalDateTime(mealTo.dateTime, 'yyy-MM-dd HH:mm')}"/></td>
            <td><c:out value="${mealTo.description}"/></td>
            <td><c:out value="${mealTo.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>