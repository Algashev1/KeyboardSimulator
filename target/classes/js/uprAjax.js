/**
 * Created by Gennadiy on 20.12.2017.
 */


function myFunc(maxTime, minTime, fullTime, error, speed, intervalID) {
    clearInterval(intervalID);
    var data = {"maxTime": maxTime, "minTime": minTime, "fullTime": fullTime, "error": error, "speed": speed};
    $.ajax
    ({
        type: "POST",
        data: data,
        url: 'AddStatistics',
        success: function (serverData) {
        },
        error: function (e) {
            alert("Произошла ошибка ajax запроса!");
        }
    });


    $('#overlay').fadeIn(400, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
        function(){ // пoсле выпoлнения предъидущей aнимaции
            $("#mask").remove();
            $('#modal_form')
                .css('display', 'block') // убирaем у мoдaльнoгo oкнa display: none;
                .animate({opacity: 1, top: '50%'}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
            $("#fullTime").text("Время прохождения: " + fullTime + " сек");
            $("#speedNew").text("Средняя скорость: " + speed + " зн/мин");
            if (minTime == 10000)
            {
                minTime = 0;
            }
            $("#maxTime").text("Максимальная скорость: " + maxTime + " зн/мин");
            $("#minTime").text("Минимальная скорость: " + minTime + " зн/мин");
            $("#countError").text("Количество ошибок: " + error);
        });
}

$(document).ready(function() {
    var maxError;
    var error = 0;
    var time;
    var timeLeft;
    var textExercise;
    var element;
    var minTime = 10000;
    var maxTime = 0;
    var fullTime = 1;
    var countElement = 0;
    var speed = 0;
    var myFlag = 0;
    var intervalID;

    $("#uprEndButton").click( function(){
        myFunc(maxTime, minTime, fullTime, error, speed, intervalID);
    });

    $('#modal_close, #overlay').click( function(){ // лoвим клик пo крестику или пoдлoжке
        $('#modal_form')
            .animate({opacity: 0, top: '45%'}, 200,  // плaвнo меняем прoзрaчнoсть нa 0 и oднoвременнo двигaем oкнo вверх
                function(){ // пoсле aнимaции
                    $(this).css('display', 'none'); // делaем ему display: none;
                    $('#overlay').fadeOut(400); // скрывaем пoдлoжку
                    window.location.href = 'UprChoose.html';
                }
            );
    });

    $("#write").keypress(function(eventObject) {
        var code = eventObject.which;
        if (code == 1072) {
            element = 'а';
        } else if (code == 1073) {
            element = 'б';
        } else if (code == 1074) {
            element = 'в';
        } else if (code == 1075) {
            element = 'г';
        } else if (code == 1076) {
            element = 'д';
        } else if (code == 1077) {
            element = 'е';
        } else if (code == 1078) {
            element = 'ж';
        } else if (code == 1079) {
            element = 'з';
        } else if (code == 1080) {
            element = 'и';
        } else if (code == 1081) {
            element = 'й';
        } else if (code == 1082) {
            element = 'к';
        } else if (code == 1083) {
            element = 'л';
        } else if (code == 1084) {
            element = 'м';
        } else if (code == 1085) {
            element = 'н';
        } else if (code == 1086) {
            element = 'о';
        } else if (code == 1087) {
            element = 'п';
        } else if (code == 1088) {
            element = 'р';
        } else if (code == 1089) {
            element = 'с';
        } else if (code == 1090) {
            element = 'т';
        } else if (code == 1091) {
            element = 'у';
        } else if (code == 1092) {
            element = 'ф';
        } else if (code == 1093) {
            element = 'х';
        } else if (code == 1094) {
            element = 'ц';
        } else if (code == 1095) {
            element = 'ч';
        } else if (code == 1096) {
            element = 'ш';
        } else if (code == 1097) {
            element = 'щ';
        } else if (code == 1098) {
            element = 'ъ';
        } else if (code == 1099) {
            element = 'ы';
        } else if (code == 1100) {
            element = 'ь';
        } else if (code == 1101) {
            element = 'э';
        } else if (code == 1102) {
            element = 'ю';
        } else if (code == 1103) {
            element = 'я';
        } else if (code == 1105) {
            element = 'ё';
        } else if (code == 32) {
            element = ' ';
        }

        countElement = countElement + 1;
        speed = countElement / fullTime;
        speed = speed * 60;

        if (speed <= minTime) {
            minTime = speed.toFixed();
        }

        if (speed >= maxTime) {
            maxTime = speed.toFixed();
        }

        speed = speed.toFixed();

        $("#speed").text("Скорость: " + speed + " зн/мин");


        if (element == textExercise.charAt(0)) {
            if (textExercise.length == 1) {
                $("#read").text("");
                myFunc(maxTime, minTime, fullTime, error, speed, intervalID);
            }
            else {
                textExercise = textExercise.substr(1);
                var charOne = textExercise[0];
                if (charOne == " ")
                {
                    charOne = "&nbsp;"
                }
                $("#read").text("");
                $("#read").append("<span style=\"color: #ca6dff; background-color: #f8d86c;\">" + charOne + "</span>");
                $("#read").append(textExercise.substr(1));

                timeLeft = time;
                $("#time").text("Время: " + timeLeft + " сек");
            }
        }
        else {
            myFlag = 1;
            error = error + 1;
            $("#error").text("Ошибки: " + error + "/" + maxError);
            if (error == maxError) {
                myFunc(maxTime, minTime, fullTime, error, speed, intervalID);
            }

        }
    });

    $("#write").on('input',function(e){
        if (myFlag == 1)
        {
            myFlag = 0;
            var myText = $("#write").val();
            var myNewText = myText.slice(0, -1);
            $("#write").val(myNewText);
        }
    });


    intervalID = setInterval(function() {
        timeLeft = timeLeft - 1;
        $("#time").text("Время: " + timeLeft + " сек");
        fullTime = fullTime + 1;
        if (timeLeft == 0 ) {
            myFunc(maxTime, minTime, fullTime, error, speed, intervalID);
        }
    }, 1000);

    $(window).load(function () {
        $('#write').focus();
        var path = window.location.href;
        var arr = path.split('?');
        var data = {"exerciseId" :arr[1]};
        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'LevelByExercise',
            success:function(serverData)
            {
                maxError = serverData.maxError;
                time = serverData.time;
                timeLeft = time;
                textExercise = serverData.text;
                var charOne = textExercise[0];

                $("#read").append("<span style=\"color: #ca6dff; background-color: #f8d86c;\">" + charOne + "</span>");
                $("#read").append(textExercise.substr(1));

                $("#read").attr("disabled", "disabled ");
                $("#error").text("Ошибки: 0/" + maxError);
                $("#time").text("Время: " + time + " сек");

            },
            error: function(e)
            {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    });

    $("#checkboxKeyboard").change(function () {
        $('#write').focus();
        });
});