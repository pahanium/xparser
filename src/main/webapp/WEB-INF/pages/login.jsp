<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <link rel='stylesheet' href='webjars/bootstrap/3.3.6/css/bootstrap.min.css'>

    <title>XParser</title>
</head>
<body>

<div align="center">
    <c:url value="/j_spring_security_check" var="loginUrl" />

    <form action="${loginUrl}" method="POST">
        Login: <input type="text" name="j_login"><br/>
        Password: <input type="password" name="j_password"><br/>
        <input type="submit" />

        <c:if test="${param.error ne null}">
            <p>Wrong login or password!</p>
        </c:if>
    </form>
</div>

<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
