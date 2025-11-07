<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://beispiel3123.de/tags/repeat" prefix="ct" %>
<!DOCTYPE html>
<html>
<head>
    <title>Test Repeats</title>
</head>
<body>
<h1>Test Repeats</h1>

<ct:repeat times="3">
    <p>Dies ist ein wiederholter Text!</p>
</ct:repeat>

<p><fit:repeat times="0">wird times mit 0 angezeigt?</fit:repeat></p>

<p><fit:repeat times="-3">wird times mit -3 angezeigt?</fit:repeat></p>

<p><fit:repeat times="abc">wird times mit abc angezeigt?</fit:repeat></p>


<h1>Auswertung</h1>
<a href="form.jsp">Zur Auswertung</a>

</body>
</html>