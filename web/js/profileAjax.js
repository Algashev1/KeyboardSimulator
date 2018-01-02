/**
 * Created by Gennadiy on 20.12.2017.
 */


$(document).ready(function(){

    $("#changeData").keyup(function() {
        var name = $('#username').val();
        var password = $('#password').val();

        if(name.length != 0 && password.length != 0) {
            $('#changeData').removeAttr('disabled');
        } else {
            $('#changeData').attr('disabled', 'disabled');
        }
    });

    $("#changeData").click(function(){
        var name = $('#username').val();
        var password = $('#password').val();
        if(name.length != 0 && password.length != 0) {
            var data = {
                "login": $("#username").val(),
                "password": $("#password").val(),
                "levelId": $('input[name=isHard]:checked').val()
            };
            $.ajax
            ({
                type: "POST",
                data: data,
                url: 'ChangeUserProfile',
                success: function (serverData) {
                    if (serverData.error == -1) {
                        $("#auth-info").html("Данные не были изменены. Пользователь с таким логином уже зарегестрирован в системе.");
                        $("#auth-info").attr("class", "error_msg")
                    }
                    else {
                        $('#userData').trigger('load');
                        $("#auth-info").html("Данные были успешно изменены! <br> &nbsp;");
                        $("#auth-info").attr("class", "good_msg")
                    }
                },
                error: function (e) {
                    alert("Произошла ошибка ajax запроса!");
                }
            });
        }
        else {
            $("#auth-info").html("Данные не были изменены. <br> Пожалуйста, заполните все поля!");
            $("#auth-info").attr("class", "error_msg")
        }
    });

    $("#userData").load(function(){
        var data = {};
        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'UserProfile',
            success:function(serverData)
            {
                $("#hello").text("Здравствуй, " + serverData.login + "!");
                $("#username").val(serverData.login);
                $("#password").val(serverData.password);
                var levelId = serverData.levelId;
                if (levelId == 1) {
                    $("#radio1").attr("checked",true);
                }
                else if (levelId == 2) {
                    $("#radio2").attr("checked",true);
                }
                else if (levelId == 3) {
                    $("#radio3").attr("checked",true);
                }
                else if (levelId == 4) {
                    $("#radio4").attr("checked",true);
                }
            },
            error: function(e)
            {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    });

    $(window).load(function() {
        $('#userData').trigger('load');
    });
});