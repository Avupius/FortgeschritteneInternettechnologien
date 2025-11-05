<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head><title>Praktikum 2</title></head>
<body>
<h2>Index.jsp</h2>
<p>Aufgabe 1: <a href="initparams">InitParameterServlet</a></p>

<p>Aufgabe 2: <a href="requestinfo">HTTP-Daten-Servlet</a></p>

<form method="get" action="requestinfo">
    Daten 1: <input type="text" name="Name1" /> <br>
    Daten 2: <input type="text" name="Name2" /> <br>
    <input type="submit" value="Abschicken" />
</form>

<!-- Formular: POST mit Checkboxen -->
<h3>Checkbox-Test (POST)</h3>
<form method="post" action="requestinfo">
    <p>Wähle eine Option aus einer Gruppe:</p>

    <strong>Gruppe A:</strong><br>
    <input type="checkbox" name="gruppeA" value="A1"> A1<br>
    <input type="checkbox" name="gruppeA" value="A2"> A2<br>
    <input type="checkbox" name="gruppeA" value="A3"> A3<br><br>

    <strong>Gruppe B:</strong><br>
    <input type="checkbox" name="gruppeB" value="B1"> B1<br>
    <input type="checkbox" name="gruppeB" value="B2"> B2<br><br>

    <input type="submit" value="Abschicken (POST)" />
</form>

<p>Aufgabe 3: <a href="time">Time-Servlet</a></p>

</body>
</html>
