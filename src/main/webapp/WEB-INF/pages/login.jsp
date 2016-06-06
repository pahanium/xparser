<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>XParser - Login</title>

    <link rel='stylesheet' href='webjars/bootstrap/3.3.6/css/bootstrap.min.css'>

    <link rel='stylesheet' href='pages/css/main.css'>
</head>
<body>

<div align="center">
    <div class="container">
        <c:url value="/j_spring_security_check" var="loginUrl" />
        <form action="${loginUrl}" method="POST" class="form-login">
            <h2 class="form-login-heading">Please log in</h2>
            <c:if test="${param.error ne null}">
                <div class="alert alert-danger login-error" role="alert">Wrong login or password!</div>
            </c:if>
            <label for="inputLogin" class="sr-only">Login</label>
            <input type="text" id="inputLogin" name="j_login" class="form-control" placeholder="Login" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" name="j_password" class="form-control" placeholder="Password" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
        </form>
    </div> <!-- /container -->
</div>

</body>
</html>
