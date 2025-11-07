<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Formular</title>
</head>
<body>

<h2>Bitte geben Sie Ihre Daten ein:</h2>

<form action="formData.jsp" method="post">
    Name: <input type="text" name="name"><br/>
    Stadt: <input type="text" name="city"><br/>
    Hobbys:<br/>
    <input type="checkbox" name="hobby" value="Lesen"> Lesen<br/>
    <input type="checkbox" name="hobby" value="Sport"> Sport<br/>
    <input type="checkbox" name="hobby" value="Reisen"> Reisen<br/><br/>
    <input type="submit" value="Absenden">
</form>

<a href="index.jsp">Zurück zum Repeat</a>

</body>
</html>
