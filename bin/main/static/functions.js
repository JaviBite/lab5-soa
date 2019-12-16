$(document).ready(function () {
    registerSearch();
    registerTemplate();
});

function registerSearch() {
    $("#search").submit(function (ev) {
        event.preventDefault();
        $.get($(this).attr('action'), {q: $("#q").val(),
                                       n: $("#n").val(),
                                       p: $("#p").val(),
                                       l: $("#l").val()}, function (data) {
            $("#resultsBlock").html(Mustache.render(template, data));
        });
    });
}

function registerTemplate() {
    template = $("#template").html();
    Mustache.parse(template);
}
