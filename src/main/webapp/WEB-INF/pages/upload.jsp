<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>XParser - Upload</title>

    <link rel='stylesheet' href='/webjars/bootstrap/3.3.6/css/bootstrap.min.css'>
    <link rel='stylesheet' href='/css/main.css'>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">XParser</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/">Home</a></li>
                <li><a href="/admin/settings">Settings</a></li>
                <li><a href="/help">Help</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="/">Overview <span class="sr-only">(current)</span></a></li>
                <li><a href="/admin/parser-list">Parser list</a></li>
                <li><a href="/admin/parser-add">Add New Parser</a></li>
                <li><a href="/upload-list">Uploads</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="/admin/settings">Settings</a></li>
                <li><a href="/help">Help</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <c:if test="${not empty message}">
                <div class="alert alert-success" role="alert">${message}</div>
            </c:if>
            <c:if test="${not empty messageFail}">
                <div class="alert alert-warning" role="alert">${messageFail}</div>
            </c:if>

            <h1 class="page-header">Parse</h1>

            <p>Name : ${parser.name}</p>

            <form action="/upload?id=${parser.id}" enctype="multipart/form-data" method="post">
                <div class="form-group">
                    <label for="file">File to upload</label>
                    <input type="file" id="file" name="file" />
                </div>
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-play"></span> Upload</button>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
