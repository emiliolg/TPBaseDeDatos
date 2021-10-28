<%@ page contentType="text/html;charset=UTF-8" %>
<!--login.jsp genera el html para el login page, tiene dos inputs separados en divisiones
y pro ultimo el boton de login q manda la request al servlet-->
<html>
<head>
    <title>Biblioteca Login</title>
</head>
<body>

<a href="/">
    <img src="/img/logo.jpg">
</a>

<h1>Login</h1>

<form action="/login" method="post">
    <p>${msg}</p>
    <div>
        <label>Username:</label>
        <input type="text" name="username"/>
    </div>
    <div>
        <label>Password:</label>
        <input type="password" name="password"/>
    </div>
    <button type="submit">Login</button>
</form>

</body>
</html>