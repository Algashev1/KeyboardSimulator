/**
 * Created by Gennadiy on 28.12.2017.
 */

$(document).ready(function(){
    $("#regbutton").click(function () {
        var login = $("#usernamesignup").val();
        var password1 = $("#passwordsignup").val();
        var password2 = $("#passwordsignup_confirm").val();
        if (!login == "" && !password1 == "" && !password2 == "") {
            if (password1 == password2) {
                var data = {"login": login, "password1": password1, "password2": password2};
                $.ajax
                ({
                    type: "POST",
                    data: data,
                    url: 'Registration',
                    success: function (serverData) {
                        var result = serverData.result;
                        if (result == -1) {
                            $("#auth-info").html("Не удалось выполнить регистрацию. Пользователь с таким логином уже существует.");
                            $("#auth-info").attr("class", "error_msg");
                        }
                        else {
                            var url = "http://localhost:8080/index.html?reg";
                            $(location).attr('href', url);
                        }
                    },
                    error: function (e) {
                        alert("Произошла ошибка ajax запроса!");
                    }
                });
            }
            else
            {
                $("#auth-info").html("Не удалось выполнить регистрацию! Пароли не совпадают.");
                $("#auth-info").attr("class", "error_msg");
            }
        }
        else if (login == "" || password1 == "" || password2 == "")
        {
            $("#auth-info").html("Не удалось выполнить регистрацию! Заполните все поля.");
            $("#auth-info").attr("class", "error_msg");
        }
    });
});