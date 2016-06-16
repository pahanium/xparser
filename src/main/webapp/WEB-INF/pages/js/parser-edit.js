$(function() {
    // Start indexing at the size of the current list
    var index = 10; //TODO

    // Add a new Field
    $("a.fields-button-add").on("click", function() {
        $("#fields-sort").append(function() {
            var html = '<div id="fields-' + index + '-wrapper" class="panel panel-default" style="display: none">\n';
            html += '   <div class="form-inline fields-wrapper panel-heading">\n';
            html += '       <span class="glyphicon glyphicon-move"></span>\n';
            //html += '       <input type="hidden" id="fields-' + index + '-remove" name="fields[' + index + '].remove" value="0">\n';
            html += '       <div class="form-group">\n';
            html += '           <label for="fields-' + index + '-title">Title</label>\n';
            html += '          <input type="text" class="form-control" id="fields-' + index + '-title" name="fields[' + index + '].title" placeholder="Title" required>\n';
            html += '       </div>\n';
            html += '       <div class="form-group">\n';
            html += '           <label for="fields-' + index + '-column">Column</label>\n';
            html += '           <input type="text" class="form-control" id="fields-' + index + '-column" name="fields[' + index + '].column" placeholder="Column" required>\n';
            html += '       </div>\n';
            html += '       <div class="form-group">\n';
            html += '           <label for="fields-' + index + '-fweight">Weight</label>\n';
            html += '           <input type="text" class="form-control" id="fields-' + index + '-fweight" name="fields[' + index + '].weight" size="4" placeholder="Weight" required>\n';
            html += '       </div>\n';
            html += '       <a href="#" class="fields-button-remove btn btn-danger" data-index="' + index + '"><span class="glyphicon glyphicon-trash"></span> Delete</a>\n';
            html += '   </div>\n';
            html += '   <div class="panel-body">\n';
            html += '       <div id="fields-' + index + '-functions-sort">\n';
            html += '       </div>\n';
            html += '       <a href="#" class="functions-button-add btn btn-success" data-parent="' + index + '"><span class="glyphicon glyphicon-plus"></span> Add Function</a>\n';
            html += '   </div>\n';
            html += '</div>\n';
            return html;
        });
        $("#fields-" + index + "-wrapper").show();
        $("#fields-" + index + "-title").focus();

        // Reload sort
        $("#fields-sort").sortable('reload');
        // Reindex weight
        $("#fields-sort input[id$='fweight']").each(function(index, element){
            element.value = index + 1;
        });

        // Add Functions sort
        addSort($('#fields-' + index + '-functions-sort'));

        index++;
        return false;
    });

    // Remove a Field
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

    // Add a new Function
    $("div.fields-panel").on("click", "a.functions-button-add", function() {
        var parent = $(this).data("parent");

        $("#fields-" + parent + "-functions-sort").append(function() {
            var html = '<div id="fields-' + parent + '-functions-' + index +'-wrapper" class="form-inline functions-wrapper" style="display: none">\n';
            html += '   <span class="glyphicon glyphicon-move"></span>\n';
            //html += '   <input type="hidden" id="fields-' + parent + '-functions-' + index +'-remove" name="fields[' + parent + '].functions[' + index +'].remove" value="0">\n';
            html += '   <div class="form-group">\n';
            html += '       <label for="fields-' + parent + '-functions-' + index +'-type">Type</label>\n';
            html += '       <select class="form-control" id="fields-' + parent + '-functions-' + index +'-type" name="fields[' + parent + '].functions[' + index +'].type" placeholder="Type">\n';
            html += $("#optionsFunctionTypeEnum").html();
            html += '       </select>\n';
            html += '   </div>\n';
            html += '   <div class="form-group">\n';
            html += '       <label for="fields-' + parent + '-functions-' + index +'-params">Params</label>\n';
            html += '       <input type="text" class="form-control" id="fields-' + parent + '-functions-' + index +'-params" name="fields[' + parent + '].functions[' + index +'].params" placeholder="Params">\n';
            html += '   </div>\n';
            html += '   <div class="form-group">\n';
            html += '       <label for="fields-' + parent + '-functions-' + index +'-weight">Weight</label>\n';
            html += '       <input type="text" class="form-control" id="fields-' + parent + '-functions-' + index +'-weight" name="fields[' + parent + '].functions[' + index +'].weight" size="4" placeholder="Weight">\n';
            html += '   </div>\n';
            html += '   <a href="#" class="functions-button-remove btn btn-danger" data-parent="' + parent + '" data-index="' + index + '"><span class="glyphicon glyphicon-trash"></span> Delete</a>\n'
            html += '</div>\n';
            return html;
        });
        $("#fields-" + parent + "-functions-" + index + "-wrapper").show();
        $("#fields-" + parent + "-functions-" + index + "-type").focus();
        index++;

        // Reload sort
        $('#fields-' + parent + '-functions-sort').sortable('reload');
        // Set new weight all functions
        $("#fields-" + parent + "-functions-sort input[name$='weight']").each(function(index, element){
            element.value = index + 1;
        });

        return false;
    });

    // Remove a Function
    $("div.fields-panel").on("click", "a.functions-button-remove", function() {
        var index2remove = $(this).data("index");
        var parent = $(this).data("parent");
        if ($("#fields-" + parent + "-functions-" + index2remove + "-id").length) {
            // If function from db, hide it
            $("#fields-" + parent + "-functions-" + index2remove + "-wrapper").hide();
            $("#fields-" + parent + "-functions-" + index2remove + "-remove").val("1");
        } else {
            // Else remove
            $("#fields-" + parent + "-functions-" + index2remove + "-wrapper").remove();
        }
        return false;
    });

    $("#fields-sort").sortable({
        //option
        forcePlaceholderSize: true
    }).bind("sortstop", function (e, ui) {
        $("#fields-sort input[id$='fweight']").each(function(index, element){
            element.value = index + 1;
        });
    });

    //add sort for each loaded fields-functions-sort div
    $(".fields-functions-sort").each(function(){
        addSort($(this));
    });
});

// Add sort
function addSort($container) {
    $container.sortable({
        //option
        forcePlaceholderSize: true
    }).bind("sortstop", function (e, ui) {
        $container.find("input[id$='weight']").each(function (index, element) {
            element.value = index + 1;
        });
    });
};
