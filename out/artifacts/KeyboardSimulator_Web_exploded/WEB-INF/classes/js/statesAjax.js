/**
 * Created by Gennadiy on 25.12.2017.
 */

$(document).ready(function(){

    $("input[name=isTime]").change(function () {
    var type = $('input[name="isView"]:checked');
    type.trigger("click");

})

    $(window).load(function(){
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        if (hashes != "http://localhost:8080/States.html")
        {
            var data = {"userId": hashes[0]};
            $.ajax
            ({
                type: "POST",
                data: data,
                url: 'ChartByUser',
                success: function (serverData) {
                },
                error: function (e) {
                    alert("Произошла ошибка ajax запроса!");
                }
            });
        }
    });
});
