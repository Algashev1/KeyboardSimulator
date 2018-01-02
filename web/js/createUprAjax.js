/**
 * Created by Gennadiy on 17.12.2017.
 */

$(document).ready(function(){

    $("input[name=isHard]").change(function () {
        var level = $('input[name="isHard"]:checked').val();
        if (level == 1) {
            $("#keyboard").attr("src", "images/1.png");
        } else if (level == 2) {
            $("#keyboard").attr("src", "images/2.png");
        } else if (level == 3) {
            $("#keyboard").attr("src", "images/3.png");
        } else if (level == 4) {
            $("#keyboard").attr("src", "images/4.png");
        }
    })

    $("#autoGenerate").click(function(){
        var data = {"id": $('input[name=isHard]:checked').val()};
        $.ajax
        ({
            type: "POST", //Метод передачи
            data: data, //Передаваемые данные в JSON - формате
            url: 'GetCreateUpr',//Название сервлета
            success:function(serverData)//Если запрос удачен
            {
                $('.textareaFAQ').text(serverData.result);
            },
            error: function(e)
            {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    });

    $("#uploadFromFile").change(function(){
        var fileName = $(this).val();
        var pos = fileName.lastIndexOf("\\");
        if(pos != -1)
        {
            fileName = fileName.substr(pos+1);
        }
        var data = {"path": fileName, "id": $('input[name=isHard]:checked').val()};
        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'GetLoadUpr',
            success:function(serverData)
            {
                var result = serverData.result;
                if (result == "-1")
                {
                    $("#auth-info").html("Не верный формат загружаемого файла: файл должен быть формата .txt");
                    $("#auth-info").attr("class", "error_msg");
                    $('.textareaFAQ').text("");
                }
                else if (result == "-2")
                {
                    $("#auth-info").html("Файл не найден");
                    $("#auth-info").attr("class", "error_msg");
                    $('.textareaFAQ').text("");
                }
                else if (result == "-3")
                {
                    $("#auth-info").html("Ошибка логики обработки запроса, обратитесь к разработчикам");
                    $("#auth-info").attr("class", "error_msg");
                    $('.textareaFAQ').text("");
                }
                else
                {
                    var result = serverData.result;
                    if (confirm("В файле могут присутствовать недопустимые символы. Удалить их?"))
                    {
                        var zone = serverData.zone.split(',');
                        var newResult = "";
                        for (var i = 0; i < result.length; i++)
                        {
                            if ( zone.indexOf(result[i]) != -1 || result[i] == " ")
                            {
                                newResult = newResult + result[i];
                            }
                        }

                        result = newResult.replace(/\s+/g," ");
                    }
                    $("#auth-info").html("Упражнение было успешно загружено");
                    $("#auth-info").attr("class", "good_msg");
                    $('.textareaFAQ').text(result);
                }
            },
            error: function(e)
            {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    });

    $("#uprCreateButton").click(function(){
        var value = $('.textareaFAQ').val();
        var data = {"id": $('input[name=isHard]:checked').val(), "value": value};
        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'CreateExercise',
            success:function(serverData)
            {
                var result = serverData.result;
                if (result == -1)
                {
                    $("#auth-info").html("Произошла ошибка при сохранении упражнения в базу данных");
                    $("#auth-info").attr("class", "error_msg");
                }
                else if (result == -2)
                {
                    $("#auth-info").html("Не удалось создать упражнение: длина упражнения меньше, чем минимальная");
                    $("#auth-info").attr("class", "error_msg");
                }
                else if (result == -3)
                {
                    $("#auth-info").html("Не удалось создать упражнение: длина упражнения больше чем максимальная");
                    $("#auth-info").attr("class", "error_msg");
                }
                else if (result == -4)
                {
                    $("#auth-info").html("Не удалось создать упражнение: в упражнении присутствуют недопустимые символы");
                    $("#auth-info").attr("class", "error_msg");
                }
                else
                {
                    $("#auth-info").html("Упражнение было успешно создано и сохранено");
                    $("#auth-info").attr("class", "good_msg");
                }
            },
            error: function(e)
            {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    });
});