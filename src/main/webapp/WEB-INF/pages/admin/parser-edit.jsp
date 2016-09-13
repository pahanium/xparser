<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                <c:choose>
                    <c:when test="${empty parser.name}">
                        <li class="active"><a href="/admin/parser-add">Add New Parser</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="/admin/parser-add">Add New Parser</a></li>
                    </c:otherwise>
                </c:choose>
                <li><a href="/upload-list">Uploads</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="/admin/settings">Settings</a></li>
                <li><a href="/help">Help</a></li>
            </ul>
        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">

            <c:if test="${not empty message}">
                <div class="alert alert-warning" role="alert">${message}</div>
            </c:if>

            <c:choose>
                <c:when test="${empty parser.name}">
                    <h1 class="page-header">Add New Parser</h1>
                </c:when>
                <c:otherwise>
                    <h1 class="page-header">Update Parser</h1>
                </c:otherwise>
            </c:choose>

            <form action="/admin/parser-edit" method="post" modelAttribute="parser">
                <input type="hidden" name="id" value="${parser.id}">
                <div class="form-group">
                    <label for="name">Parser Name</label>
                    <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${parser.name}" required autofocus>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <input type="text" class="form-control" id="description" name="description" placeholder="Description" value="${parser.description}">
                </div>

                <div class="fields-panel panel panel-default">
                    <div class="panel-heading">Fields List</div>
                    <div class="panel-body">
                        <div id="fields-sort">
                        <c:forEach items="${parser.fields}" varStatus="loop">
                            <div id="fields-${loop.index}-wrapper" class="panel panel-default">
                                <div class="form-inline fields-wrapper panel-heading">
                                    <span class="glyphicon glyphicon-move"></span>
                                    <input type="hidden" id="fields-${loop.index}-id" name="fields[${loop.index}].id" value="${parser.fields[loop.index].id}">
                                    <input type="hidden" id="fields-${loop.index}-remove" name="fields[${loop.index}].remove" value="0">
                                    <div class="form-group">
                                        <label for="fields-${loop.index}-title">Title</label>
                                        <input type="text" class="form-control" id="fields-${loop.index}-title" name="fields[${loop.index}].title" placeholder="Title" value="${parser.fields[loop.index].title}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="fields-${loop.index}-column">Column</label>
                                        <input type="text" class="form-control" id="fields-${loop.index}-column" name="fields[${loop.index}].column" placeholder="Column" value="${parser.fields[loop.index].column}" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="fields-${loop.index}-fweight">Weight</label>
                                        <input type="text" class="form-control" id="fields-${loop.index}-fweight" name="fields[${loop.index}].weight" size="4" placeholder="Weight" value="${parser.fields[loop.index].weight}" required>
                                    </div>
                                    <a href="#" class="fields-button-remove btn btn-danger" data-index="${loop.index}"><span class="glyphicon glyphicon-trash"></span> Delete</a>
                                </div>
                                <div class="panel-body">
                                    <div id="fields-${loop.index}-functions-sort" class="fields-functions-sort">
                                    <c:forEach items="${parser.fields[loop.index].functions}" varStatus="funcLoop">
                                        <div id="fields-${loop.index}-functions-${funcLoop.index}-wrapper" class="form-inline functions-wrapper">
                                            <span class="glyphicon glyphicon-move"></span>
                                            <input type="hidden" id="fields-${loop.index}-functions-${funcLoop.index}-id" name="fields[${loop.index}].functions[${funcLoop.index}].id" value="${parser.fields[loop.index].functions[funcLoop.index].id}">
                                            <input type="hidden" id="fields-${loop.index}-functions-${funcLoop.index}-remove" name="fields[${loop.index}].functions[${funcLoop.index}].remove" value="0">
                                            <div class="form-group">
                                                <label for="fields-${loop.index}-functions-${funcLoop.index}-type">Type</label>
                                                <select class="form-control" id="fields-${loop.index}-functions-${funcLoop.index}-type" name="fields[${loop.index}].functions[${funcLoop.index}].type">
                                                    <c:forEach items="${functionTypeEnum}" varStatus="ll">
                                                        <c:choose>
                                                            <c:when test="${functionTypeEnum[ll.index] eq parser.fields[loop.index].functions[funcLoop.index].type}">
                                                                <option value="${functionTypeEnum[ll.index]}" selected>${functionTypeEnum[ll.index]}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${functionTypeEnum[ll.index]}">${functionTypeEnum[ll.index]}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="fields-${loop.index}-functions-${funcLoop.index}-params">Params</label>
                                                <input type="text" class="form-control" id="fields-${loop.index}-functions-${funcLoop.index}-params" name="fields[${loop.index}].functions[${funcLoop.index}].params" placeholder="Params" value="${parser.fields[loop.index].functions[funcLoop.index].params}">
                                            </div>
                                            <div class="form-group">
                                                <label for="fields-${loop.index}-functions-${funcLoop.index}-weight">Weight</label>
                                                <input type="text" class="form-control" id="fields-${loop.index}-functions-${funcLoop.index}-weight" name="fields[${loop.index}].functions[${funcLoop.index}].weight" size="4" placeholder="Weight" value="${parser.fields[loop.index].functions[funcLoop.index].weight}" required>
                                            </div>
                                            <a href="#" class="functions-button-remove btn btn-danger" data-parent="${loop.index}" data-index="${funcLoop.index}"><span class="glyphicon glyphicon-trash"></span> Delete</a>
                                        </div>
                                    </c:forEach>
                                    </div>
                                    <a href="#" class="functions-button-add btn btn-success" data-parent="${loop.index}"><span class="glyphicon glyphicon-plus"></span> Add Function</a>
                                </div>
                            </div>
                        </c:forEach>
                        </div>
                        <a href="#" class="fields-button-add btn btn-success"><span class="glyphicon glyphicon-plus"></span> Add Field</a>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary"><span class="glyphicon glyphicon-save"></span> Save</button>
            </form>

            <div style="display: none" id="optionsFunctionTypeEnum">
                <c:forEach items="${functionTypeEnum}" varStatus="ll">
                    <option value="${functionTypeEnum[ll.index]}">${functionTypeEnum[ll.index]}</option>
                </c:forEach>
            </div>

        </div>
    </div>
</div>

<script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/html.sortable.min.js"></script>
<script type="text/javascript" src="/js/parser-edit.js"></script>

</body>
</html>
