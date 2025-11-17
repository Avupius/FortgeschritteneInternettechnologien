<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login</h2>

<form action="login" method="post">
    <label for="user">Benutzername:</label>
    <input type="text" id="user" name="username" required>

    <label for="pw">Passwort:</label>
    <input type="password" id="pw" name="password" required>

    <button type="submit">Login</button>
</form>

</body>
</html>
