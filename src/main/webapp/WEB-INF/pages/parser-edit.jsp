<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>XParser</title>

    <link rel='stylesheet' href='/webjars/bootstrap/3.3.6/css/bootstrap.min.css'>
    <link rel='stylesheet' href='/pages/css/main.css'>
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
                <li><a href="/about">About</a></li>
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
                <li class="active"><a href="/admin/parser-add">Add New Parser</a></li>
                <li><a href="#">Export</a></li>
            </ul>
            <ul class="nav nav-sidebar">
                <li><a href="#">Nav item</a></li>
                <li><a href="#">Nav item again</a></li>
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

            <form action="/admin/parser-save" method="post" modelAttribute="parser">
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
                        <c:forEach items="${parser.fields}" varStatus="loop">
                            <div id="fields-${loop.index}-wrapper" class="form-inline field-wrapper">
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
                                    <label for="fields-${loop.index}-weight">Weight</label>
                                    <input type="text" class="form-control" id="fields-${loop.index}-weight" name="fields[${loop.index}].weight" placeholder="Weight" value="${parser.fields[loop.index].weight}" required>
                                </div>
                                <a href="#" class="fields-button-remove" data-index="${loop.index}">remove</a>
                            </div>
                        </c:forEach>
                        <button id="fields-button-add" type="button" class="btn btn-primary">Add Field</button>
                    </div>
                </div>

                <button type="submit" class="btn btn-primary">Save</button>
            </form>

        </div>
    </div>
</div>

<script type="text/javascript" src="/webjars/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript" src="/webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $(function() {
        // Start indexing at the size of the current list
        var index = ${fn:length(parser.fields)};

        // Add a new Field
        $("#fields-button-add").on("click", function() {
            $(this).before(function() {
                var html = '<div id="fields-' + index + '-wrapper" class="form-inline field-wrapper" style="display: none">\n';
                html += '   <div class="form-group">\n';
                html += '       <label for="fields-' + index + '-title">Title</label>\n';
                html += '       <input type="text" class="form-control" id="fields-' + index + '-title" name="fields[' + index + '].title" placeholder="Title" required>\n';
                html += '   </div>\n';
                html += '   <div class="form-group">\n';
                html += '       <label for="fields-' + index + '-column">Column</label>\n';
                html += '       <input type="text" class="form-control" id="fields-' + index + '-column" name="fields[' + index + '].column" placeholder="Column" required>\n';
                html += '   </div>\n';
                html += '   <div class="form-group">\n';
                html += '       <label for="fields-' + index + '-weight">Weight</label>\n';
                html += '       <input type="text" class="form-control" id="fields-' + index + '-weight" name="fields[' + index + '].weight" placeholder="Weight" required>\n';
                html += '   </div>\n';
                html += '   <input type="hidden" id="fields-' + index + '-remove" name="fields[' + index + '].remove" value="0">\n';
                html += '   <a href="#" class="fields-button-remove" data-index="' + index + '">remove</a>\n';
                html += "</div>\n";
                return html;
            });
            $("#fields-" + index + "-wrapper").show();
            $("#fields-" + index + "-title").focus();
            index++;
            return false;
        });

        // Remove an Field
        $("div.fields-panel").on("click", "a.fields-button-remove", function() {
            var index2remove = $(this).data("index");

            if ($("#fields-" + index2remove + "-id").length) {
                // If field from db, hide it
                $("#fields-" + index2remove + "-wrapper").hide();
                $("#fields-" + index2remove + "-remove").val("1");
            } else {
                // Else remove
                $("#fields-" + index2remove + "-wrapper").remove();
            }
            return false;
        });
    });
</script>

</body>
</html>
