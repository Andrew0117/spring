<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MCs Client</title>

</head>
<body>
    <form action="/login" method="post">
        <h2>User ID<input type="text" name="username"/></h2>
        <h2>Password<input type="password" name="password"/></h2>
        <h2><span>${message}</span></h2>
        <input type="submit" value="login"/>
    </form>

</body>
</html>