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
                <%--<li><a href="/admin/settings">Settings</a></li>--%>
                <li class="active"><a href="/help">Help</a></li>
                <li><a href="/logout">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <ul class="nav nav-sidebar">
                <li><a href="/">Overview</a></li>
                <li><a href="/admin/parser-list">Parser list</a></li>
                <li><a href="/admin/parser-add">Add New Parser</a></li>
                <li><a href="/upload-list">Uploads</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <%--<li><a href="/admin/settings">Settings</a></li>--%>
                <li class="active"><a href="/help">Help</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Help</h1>

            <p>This is demo project. Used Spring boot, Spring data, Sring security and other.</p>
            <p>Main features is converting Excel file to csv.
                For example some internet shop has import csv file. But providers of product give own price in different Excel format.
                This application help convert Excel file to csv file. You must specify required columns and functions for converting in your parser.
                Then upload Excel file. After this you can download csv file for internet shop.
            </p>
            <p>For example download <a href="/export-test">test.xlsx</a> Excel file and test on parser named "test".</p>
            </p>
            <p>
                Also, your can setup few parser on different Excel price. You can setup witch column and how it should converted to exported csv file.
                For transformation values of Excel file you can use this function:
            </p>
            <ul>
                <li><strong>Trim</strong> - leading and trailing whitespace removed</li>
                <li><strong>Replace</strong> - replaces each substring of value that matches the given regular expression with the given replacement. For example, replace "," to "."</li>
                <li><strong>Multiplication</strong> - multiplied by the number. For example, multiplied price by the currency</li>
                <li><strong>Concat</strong> - concat value of this column (<code>{value}</code>) with other processed column (<code>{column_name}</code>) or simple text.
                    For example for column "title" you can concat "brand" with this <em>params</em> <code>{brand}; ;{value}</code></li>
                <li><strong>SkipEmpty</strong> - this function allow skip some row with empty value of column</li>
            </ul>

            <p>For some function need specify parameters. If more then one parameters, you must divide them by <code>;</code>. For example, <em>params</em> for replace function <code>,;.</code></p>
            <p>User <code>admin</code> has all permissions. User <code>demo</code> has no access to edit parser or add new.</p>
        </div>
    </div>
</div>

<script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</body>
</html>
