window.onload = menuChoose;
var isView;
var isTime;


function main()
{
	"use strict";
	menuChoose();
	cicleNonStop();
}

function menuChoose() 
{
	"use strict";
	var newLi1 = document.createElement('li');
	var newLi2 = document.createElement('li');
	var newLi3 = document.createElement('li');
	var menuIn = document.getElementById("menu");
	var profileNumber = 1; //вот тут вытаскиваем из БД, какие у пользователя права доступа
    newLi1.innerHTML = '<a href="Profile.html">Профиль</a>';
    menuIn.appendChild(newLi1);
    newLi2.innerHTML = '<a href="UprChoose.html">Выбор упражнения</a>';
    menuIn.appendChild(newLi2);
    newLi3.innerHTML = '<a href="States.html" class="current">Статистика</a>';
    menuIn.appendChild(newLi3);
}

function cicleNonStop()
{
	"use strict";
	chooseView();
	chooseTime();
	switch (isView)
		{
			case 'graph':
				graphDrawing(isTime);
				break;
			case 'circle':
				circleDrawing(isTime);
				break;
			case 'table':
				tableBuild(isTime);
				break;
		}
	//а вот тут надо добавить финтифлюшку, которая нам будет обновлять всё наше удовольствие в реальном времени, тип каждое переключение любой радиогруппы запускает новую волну проверок и рисовалок
}

function chooseView()
{
	"use strict";
	var isV = document.getElementsByName('isView');
    for (var i = 0; i < isV.length; i++) {
        if (isV[i].type === "radio" && isV[i].checked) {
            /*alert("selected: " + isV[i].value);*/
			isView = isV[i].value;
        }
    }
}

function chooseTime()
{
	"use strict";
	var isT = document.getElementsByName('isTime');
    for (var i = 0; i < isT.length; i++) {
        if (isT[i].type === "radio" && isT[i].checked) {
            /*alert("selected: " + isT[i].value);*/
			isTime = isT[i].value;
        }
    }
}

//график
function graphDrawing()
{
	"use strict";
	/*isTime=isTimeshko;*/
    $("#usersTable").remove();
    $("#costil").remove();
    var element = $("#statesContainer");
    $("#popChart").remove();
    element.append("<canvas id=\"popChart\"></canvas>");
    var popCanvas = $("#popChart");
    var data = {"date":  $("input[name=isTime]:checked").val()};
    $.ajax
    ({
        type: "POST",
        data: data,
        url: 'Chart',
        success:function(serverData)
        {
            var newData = serverData.errors.split(",");
            var newLabels = serverData.labels.split(",");

            var pieChart = new Chart(popCanvas, {
                type: 'line',
                data: {
                    labels: newLabels,
                    datasets: [{
                        label: "Статистика по ошибкам",
                        data: newData,
                    }]
                },
                options: {
                    title: {
                        display: true,
                        text: "Статистика по ошибкам"
                    }
                }
            });

        },
        error: function(e)
        {
            alert("Произошла ошибка ajax запроса!");
        }
    });
}

//круговая
function circleDrawing()
{
	"use strict";
	/*isTime=isTimeshko;*/
    $("#usersTable").remove();
    $("#costil").remove();
	var element = $("#statesContainer");
	$("#popChart").remove();
	element.append("<canvas id=\"popChart\"></canvas>");
	var popCanvas = $("#popChart");
	/*if (popCanvas.getContext){var ctx = popCanvas.getContext('2d'); ctx.clearRect(0, 0, popCanvas.width, popCanvas.height);}*/
    var max = 0;
    var min = 0;
    var average = 0;
    var data = {"date":  $("input[name=isTime]:checked").val()};
    $.ajax
    ({
        type: "POST",
        data: data,
        url: 'PieChart',
        success:function(serverData)
        {
            max = serverData.max;
            min = serverData.min;
            average = serverData.average;
            var pieChart = new Chart(popCanvas, {
                type: 'pie',
                data: {
                    labels: ["Максимальная скорость", "Минимальная скорость", "Средняя скорость"],
                    datasets: [{
                        label: "Статистика скорости набора текста",
                        backgroundColor: ["#3e95cd", "#8e5ea2","#3cba9f"],
                        data: [max,min,average]
                    }]
                },
                options: {
                    title: {
                        display: true,
                        text: 'Статистика скорости набора текста'
                    }
                }
            });
        },
        error: function(e)
        {
            alert("Ошибка в ajax!");
        }
    });
}

//таблица
function tableBuild(isTimeshko)
{
	// "use strict";
	// isTime=isTimeshko;
    $("#usersTable").remove();
    $("#costil").remove();
    var element = $("#statesContainer");
    $("#popChart").remove();
    var div = $("#statesContainer");
    //div.append("<div id='costil'>&nbsp;<br>&nbsp;</div>")
    var table = $("<table>");
    table.attr("width", "100%");
    table.attr("border", "3px");
    table.attr("cellpadding", "5");
    table.attr("id", "usersTable");
    var tr1 = $("<tr>");
    tr1.append("<th scope=\"col\" width=\"150px\">Уровень сложности</th>");
    tr1.append("<th scope=\"col\" width=\"150px\">Количество ошибок</th>");
    tr1.append("<th scope=\"col\" width=\"150px\">Время выполнения</th>");
    tr1.append("<th scope=\"col\" width=\"150px\">Макисмальная скорость</th>");
    tr1.append("<th scope=\"col\" width=\"150px\">Минимальная скорость</th>");
    tr1.append("<th scope=\"col\" width=\"150px\">Средняя скорость</th>");
    tr1.append("<th scope=\"col\" width=\"150px\">Дата</th>")
    table.append(tr1);

    var data = {"date": $("input[name=isTime]:checked").val()};
    $.ajax
    ({
        type: "POST",
        data: data,
        url: 'AllStatistics',
        success:function(serverData)
        {
            var max = serverData.max.split(";");
            var min = serverData.min.split(";");
            var average = serverData.average.split(";");
            var error = serverData.error.split(";");
            var strDate = serverData.strDate.split(";");
            var time = serverData.time.split(";");
            var level = serverData.level.split(";");
            var count = max.length - 1;
            for (var i = 0; i < count; i++)
            {
                var tr2 =  $("<tr>");
                if (level[i] == 1) {
                    tr2.append("<td width=\"150px\">Лёгкий</td>");
                }
                else if (level[i] == 2) {
                    tr2.append("<td width=\"150px\">Средний</td>");
                }
                else if (level[i] == 3) {
                    tr2.append("<td width=\"150px\">Сложный</td>");
                }
                else if (level[i] == 4) {
                    tr2.append("<td width=\"150px\">Профессионал</td>");
                }
                tr2.append("<td width=\"150px\">" + error[i]+ "</td>");
                tr2.append("<td width=\"150px\">" + time[i]+ "</td>");
                tr2.append("<td width=\"150px\">" + max[i]+ "</td>");
                tr2.append("<td width=\"150px\">" + min[i]+ "</td>");
                tr2.append("<td width=\"150px\">" + average[i]+ "</td>");
                var newDate = strDate[i].split(" ");
                tr2.append("<td width=\"150px\">" + newDate[0]+ "</td>");
                table.append(tr2);
            }
            element.append(table);
        },
        error: function(e)
        {
            alert("Произошла ошибка ajax запроса!");
        }
    });

    div.append(table);

}

function gistoDrawing(){
	"use strict";
    $("#usersTable").remove();
    $("#costil").remove();
    var element = $("#statesContainer");
    $("#popChart").remove();
    element.append("<canvas id=\"popChart\"></canvas>");
    var popCanvas = $("#popChart");
	/*if (popCanvas.getContext){var ctx = popCanvas.getContext('2d'); ctx.clearRect(0, 0, popCanvas.width, popCanvas.height);}*/


    var max = 0;
    var min = 0;
    var average = 0;
    var data = {"date":  $("input[name=isTime]:checked").val()};
    $.ajax
    ({
        type: "POST",
        data: data,
        url: 'PieChart',
        success:function(serverData)
		{
            max = serverData.max;
            min = serverData.min;
            average = serverData.average;
            var barChart = new Chart(popCanvas, {
                type: 'bar',
                data: {
                    labels: ["Максимальная скорость", "Средняя скорость", "Минимальная скорость", ""],
                    datasets: [{
                        label: "Статистика скорости набора текста",
                        data: [max, average, min, 0],
                        backgroundColor: [
                            'rgba(255, 99, 132, 0.6)',
                            'rgba(54, 162, 235, 0.6)',
                            'rgba(255, 206, 86, 0.6)',
                            'rgba(255, 206, 86, 0.6)',
                        ]
                    }]
                }
            });
        },
        error: function(e)
        {
            alert("Ошибка в ajax!");
        }
    });
}