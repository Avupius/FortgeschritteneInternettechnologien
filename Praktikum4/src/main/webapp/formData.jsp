<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Formulardaten</title>
</head>
<body>
<h2>Übertragene Formulardaten</h2>

<table border="1">
    <tr>
        <th>Parametername</th>
        <th>Wert(e)</th>
    </tr>

    <c:forEach var="p" items="${param}">
        <tr>
            <td>${p.key}</td>
            <td>
                <c:forEach var="value" items="${paramValues[p.key]}">
                    ${value}<br/>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="form.jsp">Zurück zum Formular</a>
<a href="index.jsp">Zurück zum Repeat</a>
</body>
</html>

