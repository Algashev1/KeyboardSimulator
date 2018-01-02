/**
 * Created by Gennadiy on 28.12.2017.
 */

$(document).ready(function(){

    $(window).load(function(){
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
        if (hashes != "http://localhost:8080/index.html")
        {
            $("#auth-info").html("Пользователь был успешно зарегестрирован в системе.");
            $("#auth-info").attr("class", "good_msg");
        }
    });

    $("#logbutton").click(function () {
        var login = $("#username").val();
        var password = $("#password").val();
        if (!login == "" && !password == "") {
            var data = {"login": login, "password": password};
            $.ajax
            ({
                type: "POST",
                data: data,
                url: 'Auth',
                success: function (serverData) {
                    role = serverData.role;
                    if (role == -1) {
                        $("#auth-info").html("Не удалось выполнить авторизацию. Неверный логин или пароль.");
                        $("#auth-info").attr("class", "error_msg");
                    }
                    else if (role == 1) {
                        var url = "http://localhost:8080/HardBuilding.html";
                        $(location).attr('href',url);
                    }
                    else if (role == 2) {
                        var url = "http://localhost:8080/Profile.html";
                        $(location).attr('href',url);
                    }
                },
                error: function (e) {
                    alert("Произошла ошибка ajax запроса!");
                }
            });
        }
        else if (login == "" || password == "")
        {
            $("#auth-info").html("Не удалось выполнить авторизацию! Заполните все поля.");
            $("#auth-info").attr("class", "error_msg");
        }
    });
});