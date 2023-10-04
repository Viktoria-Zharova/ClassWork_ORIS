<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Авторизация</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/style.css" >
</head>

<body>
<div class="container">
    <h1>Авторизация</h1>
    <form action="login" method="post">
        <input type="text" name="username" placeholder="Имя пользователя" required>
        <br>
        <input type="password" name="password" placeholder="Пароль" required>
        <br>
        <input type="submit" value="Войти">
        <br>
        <p>Нет аккаунта? <a href="register">Зарегистрироваться</a></p>
    </form>
</div>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<p style="color: red;"><%= error %></p>
<%
    }
%>

</body>
</html>
