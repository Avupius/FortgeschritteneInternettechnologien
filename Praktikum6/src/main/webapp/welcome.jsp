<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:choose>
<c:when test="${empty sessionScope.user}">
    <c:redirect url="login.jsp"/>
<c:otherwise>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Willkommen</title>
    </head>
    <body>
    <h2>Login erfolgreich!</h2>
    <p>Hallo <strong>${sessionScope.user}</strong>!</p>
    </body>
    </html>
</c:otherwise>
</c:when>
</c:choose>