<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login</h2>

<form action="login" method="post">

    <p></p>
    <label for="user">Benutzername:</label>
    <input type="text" id="user" name="username" required>

    <label for="pw">Passwort:</label>
    <input type="password" id="pw" name="password" required>

    <p></p><label>Weiterleitung:</label><p>
    <label>
        <input type="radio" name="mode" value="forward" checked>
        Forward
    </label>

    <label>
        <input type="radio" name="mode" value="redirect">
        Redirect
    </label>
    <br><br>
    <p></p><button type="submit">Login</button><p>
</form>

</body>
</html>
