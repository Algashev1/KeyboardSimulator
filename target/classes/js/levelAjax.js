/**
 * Created by Gennadiy on 11.12.2017.
 */

$(document).ready(function(){

    $('.levelData').bind("change keyup input click", function() {
        if (this.value.match(/[^0-9]/g)) {
            this.value = this.value.replace(/[^0-9]/g, '');
        }
    });

    $("#changeData").click(function(){
        var data = {};
        data = {"levelId": $("#lev option:selected").val(), "min":  $("#minLen").val(), "max":$("#maxLen").val(),
                "maxError": $("#maxErr").val(), "time": $("#maxTime").val(), "zone": $('input[name="isZone"]:checked').val() };
        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'SetLevel',
            success:function(serverData)
            {
                var result = serverData.result;
                if (result == -1) {
                    $("#auth-info").html("Не удалось изменить уровень сложности!");
                    $("#auth-info").attr("class", "error_msg");
                }
                if (result == -2) {
                    $("#auth-info").html("Минимальная длина упражнения не может быть меньше или равна максимальной!");
                    $("#auth-info").attr("class", "error_msg");
                }
                else if (result == -3) {
                    $('#lev').trigger('change');
                    $("#auth-info").html("Минимальная длина упражнения не может быть меньше 50!");
                    $("#auth-info").attr("class", "error_msg");
                }
                else if (result == -4) {
                    $('#lev').trigger('change');
                    $("#auth-info").html("Минимальная длина упражнения не может быть больше 500!");
                    $("#auth-info").attr("class", "error_msg");
                }
                else if (result == -5) {
                    $('#lev').trigger('change');
                    $("#auth-info").html("Максимальная длина упражнения не может быть меньше 50!");
                    $("#auth-info").attr("class", "error_msg");
                }
                else if (result == -6) {
                    $('#lev').trigger('change');
                    $("#auth-info").html("Максимальная длина упражнения не может быть больше 500!");
                    $("#auth-info").attr("class", "error_msg");
                }
                else if (result == -7) {
                    $('#lev').trigger('change');
                    $("#auth-info").html("Количество ошибок не может быть меньше 1");
                    $("#auth-info").attr("class", "error_msg");
                } else if (result == -8) {
                    $('#lev').trigger('change');
                    $("#auth-info").html("Количество ошибок не может быть больше 10");
                    $("#auth-info").attr("class", "error_msg");
                }
                else {
                    $("#auth-info").html("Уровень сложности был изменён!");
                    $("#auth-info").attr("class", "good_msg");
                    $('#lev').trigger('change');
                }
            },
            error: function(e)
            {
                alert("Ошибка в ajax!");
            }
        });
    });

    $("input[name=isZone]").change(function () {
        var zone = $('input[name="isZone"]:checked').val();
        if (zone == 6) {
            $("#keyboard").attr("src", "images/1.png");
        } else if (zone == 7) {
            $("#keyboard").attr("src", "images/2.png");
        } else if (zone == 8) {
            $("#keyboard").attr("src", "images/3.png");
        } else if (zone == 9) {
            $("#keyboard").attr("src", "images/4.png");
        }
    })

    $("#lev").change(function(){
        var data = {};
        data = {"id": $("#lev option:selected").val()};
        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'GetLevel',
            success:function(serverData)//Если запрос удачен
            {
                $("#minLen").val(serverData.min);
                $("#maxLen").val(serverData.max);
                $("#maxErr").val(serverData.maxErrors);
                $("#maxTime").val(serverData.time);
                var zone = serverData.zone;
                $("#"+zone).prop('checked', true);

                var zone = $('input[name="isZone"]:checked').val();
                if (zone == 6) {
                    $("#keyboard").attr("src", "images/1.png");
                } else if (zone == 7) {
                    $("#keyboard").attr("src", "images/2.png");
                } else if (zone == 8) {
                    $("#keyboard").attr("src", "images/3.png");
                } else if (zone == 9) {
                    $("#keyboard").attr("src", "images/4.png");
                }

            },
            error: function(e)
            {
              alert("Ошибка в ajax!");
            }
        });
    });

    $(window).load(function(){
        $('#lev').trigger('change');
    });

});
