/**
 * Created by Gennadiy on 19.12.2017.
 */

$(document).ready(function(){

    $(window).load(function(){
        var data = {};
        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'GetExercises',
            success:function(serverData)
            {
                var levelId = serverData.id;
                var arrLevelsIds = serverData.levelsId.split(';')
                var arrExerciseIds = serverData.exercisesId.split(';')
                var arrExerciseCompletedId = serverData.exerciseCompletedId.split(';');
                var count = arrExerciseIds.length;
                var number = 0;
                for (var i = 0; i < count - 1; i++)
                {
                    var ul = $("#upChoose");
                    var li = $('<li>');
                    var a = $('<a>');
                    if (arrLevelsIds[i] > levelId) {
                        a.attr("class", "closeUpr");
                    }
                    else {
                        a.attr("href", "UprTest.html?"+arrExerciseIds[i]);
                        if ($.inArray(arrExerciseIds[i], arrExerciseCompletedId) != -1) {
                            a.attr("class", "readyUpr");
                        }
                    }

                    a.val(arrExerciseIds[i]);
                    if (i == 0 || (arrLevelsIds[i-1] < arrLevelsIds[i])) {
                        number = 0;
                    }
                    number = number + 1;
                    a.text("Упражнение " + arrLevelsIds[i] + "-" + number);
                    li.append(a);
                    ul.append(li);
                }
            },
            error: function(e)
            {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    });
});
