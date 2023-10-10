<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Регистрация</title>
    <link rel="stylesheet" type="text/css" href="<%=application.getContextPath() %>/css/style.css" >
</head>

<body>
<div class="container">
    <h1>Регистрация</h1>
    <form action="register" method="post" target="_blank">
        <input type="text" name="firstName" placeholder="Имя" required>
        <br>
        <input type="text" name="secondName" placeholder="Фамилия" required>
        <br>
        <input type="text" name="username" placeholder="Имя пользователя" required>
        <br>
        <input type="number" name="age" placeholder="Возраст" required>
        <br>
        <input type="password" name="password" placeholder="Пароль" required>
        <br>
        <input type="submit" value="Зарегистрироваться">
    </form>
</div>
</body>

</html>
