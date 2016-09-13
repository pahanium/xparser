<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>XParser - Help</title>

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
                <li><a href="/logout">Logout</a></li>
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
                <li class="active"><a href="/upload-list">Uploads</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="/admin/settings">Settings</a></li>
                <li><a href="/help">Help</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Uploads</h1>

            <div class="table-responsive">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Parser Name</th>
                        <th>File Name</th>
                        <th>Date</th>
                        <th>Rows Count</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${uploads}" var="upload">
                        <tr>
                            <td>${upload.id}</td>
                            <td>${upload.parser.name}</td>
                            <td>${upload.filename}</td>
                            <td>${upload.date}</td>
                            <td>${upload.rowsCount}</td>
                            <td>
                                <a href="/export?id=${upload.id}" class="btn btn-success"><span class="glyphicon glyphicon-download-alt"></span> Download .csv</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <c:if test="${totalIndex > 1}">
            <div>
                <c:url var="firstUrl" value="/upload-list?page=1" />
                <c:url var="lastUrl" value="/upload-list?page=${totalIndex}" />
                <c:url var="prevUrl" value="/upload-list?page=${currentIndex - 1}" />
                <c:url var="nextUrl" value="/upload-list?page=${currentIndex + 1}" />
                <ul class="pagination">
                    <c:choose>
                        <c:when test="${currentIndex == 1}">
                            <li class="disabled"><a href="#">&lt;&lt;</a></li>
                            <li class="disabled"><a href="#">&lt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${firstUrl}">&lt;&lt;</a></li>
                            <li><a href="${prevUrl}">&lt;</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
                        <c:url var="pageUrl" value="/upload-list?page=${i}" />
                        <c:choose>
                            <c:when test="${i == currentIndex}">
                                <li class="active"><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="${pageUrl}"><c:out value="${i}" /></a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${currentIndex == totalIndex}">
                            <li class="disabled"><a href="#">&gt;</a></li>
                            <li class="disabled"><a href="#">&gt;&gt;</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="${nextUrl}">&gt;</a></li>
                            <li><a href="${lastUrl}">&gt;&gt;</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>
