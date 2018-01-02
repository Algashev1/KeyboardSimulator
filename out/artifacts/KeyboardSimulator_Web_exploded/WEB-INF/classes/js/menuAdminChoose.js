window.onload = menuChoose;

function menuChoose()
{
    "use strict";
    var newLi1 = document.createElement('li');
    var newLi2 = document.createElement('li');
    var newLi3 = document.createElement('li');
    var menuIn = document.getElementById("menu");
    var profileNumber = 0;
    /*	var pathAdress = location.hostname;
     alert(pathAdress);*/
    if (profileNumber === 1)
    {
        newLi1.innerHTML = '<a href="Profile.html">Профиль</a>';
        menuIn.appendChild(newLi1);
        newLi2.innerHTML = '<a href="UprChoose.html">Выбор упражнения</a>';
        menuIn.appendChild(newLi2);

        /*newLi3.innerHTML = '<a href="states.html" class="current">Статистика</a>';
         menuIn.appendChild(newLi3);*/

        newLi3.innerHTML = '<a href="States.html">Статистика</a>';
        menuIn.appendChild(newLi3);

    }
    else if (profileNumber === 0)
    {
        newLi1.innerHTML = '<a href="UprCreate.html">Создание упражнения</a>';
        menuIn.appendChild(newLi1);
        newLi2.innerHTML = '<a href="HardBuilding.html">Настройка уровня сложности</a>';
        menuIn.appendChild(newLi2);
        newLi3.innerHTML = '<a href="Users.html">Учётные записи</a>';
        menuIn.appendChild(newLi3);
    }
}/**
 * Created by Gennadiy on 29.12.2017.
 */
