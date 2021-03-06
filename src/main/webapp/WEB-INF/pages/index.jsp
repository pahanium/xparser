<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>XParser</title>

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
                <li class="active"><a href="/">Home</a></li>
                <%--<li><a href="/admin/settings">Settings</a></li>--%>
                <li><a href="/help">Help</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li class="active"><a href="/">Overview</a></li>
                <li><a href="/admin/parser-list">Parser list</a></li>
                <li><a href="/admin/parser-add">Add New Parser</a></li>
                <li><a href="/upload-list">Uploads</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <%--<li><a href="/admin/settings">Settings</a></li>--%>
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

            <h1 class="page-header">Overview</h1>

            <div class="well">
                <p>
                    This is main page of converter Excel file to csv. For more detail see <a href="/help">help</a>.
                    Here you can:
                </p>
                <ul>
                    <li>select parser to upload Excel price</li>
                    <li>download csv file of last 5 uploaded Excel file</li>
                </ul>
                <p>Downlod <a href="/export-test">test.xlsx</a> for testing with parser "Test"</p>
            </div>


            <h2 class="page-header">Upload Excel Price</h2>

            <form action="upload" method="get">
                <div class="form-group">
                    <label for="parser">Select Parser:</label>
                    <div class="input-group">
                        <select class="form-control" id="parser" name="id">
                            <c:forEach items="${parsers}" var="parser">
                                <option value="${parser.id}">${parser.name}</option>
                            </c:forEach>
                        </select>
                        <div class="input-group-button">
                            <a href="/admin/parser-add" class="btn btn-success" role="button"><span class="glyphicon glyphicon-plus"></span> Add New Parser</a>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-play"></span> New Upload</button>
            </form>

            <br><br>

            <h2 class="page-header">Last Uploads</h2>
            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th></th>
                        <th>Parser Name</th>
                        <th>File Name</th>
                        <th>Date</th>
                        <th>Rows Count</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${uploads}" var="upload">
                        <tr>
                            <td>${upload.id}</td>
                            <td>
                                <a href="/export?id=${upload.id}" class="btn btn-success"><span class="glyphicon glyphicon-download-alt"></span> Download .csv</a>
                            </td>
                            <td>${upload.parser.name}</td>
                            <td>${upload.filename}</td>
                            <td>${upload.date}</td>
                            <td>${upload.rowsCount}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="pull-right"><a href="/upload-list">Show more...</a></div>
        </div>
    </div>
</div>

<script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
