/**
 * Created by Gennadiy on 15.12.2017.
 */

$(document).ready(function(){

    var arrTR = [];
    var arrInput1 = [];
    var arrInput2 = [];
    var arrSelect1 = [];
    var arrSelect2 = [];

    $('.addButton').click(function () {
        var data = {"login":  $('#addUser').find('.textfield').val(), "password":  $('#addUser').find('.textfield2').val(),
            "level": $('#addUser').find('.keyLevel').val(), "role": $('#addUser').find('.keyType').val()};
        $.ajax
        ({
            type: "POST",
            data: data,
            url: 'AddUser',
            success:function(serverData)
            {
                if (serverData.result != -1 && serverData.result != -2)
                {
                    $("#auth-info").html("Пользователь был успешно добавлен! <br> &nbsp;");
                    $("#auth-info").attr("class", "good_msg");
                    $("#tableInfo").trigger('load');
                }
                else if (serverData.result == -2) {
                    $("#auth-info").html("Пользователь не был добавлен! <br> Не все поля были заполнены!");
                    $("#auth-info").attr("class", "error_msg");
                }
                else
                {
                    $("#auth-info").html("Пользователь не был добавлен! <br> &nbsp;");
                    $("#auth-info").attr("class", "error_msg");
                }
            },
            error: function(e)
            {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    });
    $(document).delegate(".stateButton", "click",   function()  {
        var url = "http://localhost:8080/StatesAdmin.html?"+$(this).data('userId');
        $(location).attr('href',url);
    });

    $(document).delegate(".deleteButton", "click",   function()  {
        var number = $(this).data('number');
        var answer = confirm("Вы действительно хотите удалить пользователя " + arrInput1[number].val() + "?");
        if (answer) {
            var data = {"id": $(this).data('userId')};
            $.ajax
            ({
                type: "POST",
                data: data,
                url: 'DeleteUser',
                success: function (serverData) {
                    $("#auth-info").html("Пользователь был успешно удалён! <br> &nbsp;");
                    $("#auth-info").attr("class", "good_msg");
                    $("#tableInfo").trigger('load');
                },
                error: function (e) {
                    alert("Произошла ошибка ajax запроса!");
                }
            });
        }
    });

    $(document).delegate(".changeButton", "click",  function()  {
        var number = $(this).data('number');
        var name = arrInput1[number].val();
        var password = arrInput2[number].val();
        if(name.length != 0 && password.length != 0) {
            var data = {
                "userId": $(this).data('userId'), "login": arrInput1[number].val(), "password": arrInput2[number].val(),
                "level": arrSelect1[number].val(), "role": arrSelect2[number].val()
            };
            $.ajax
            ({
                type: "POST",
                data: data,
                url: 'ChangeUser',
                success: function (serverData) {
                    if (serverData.error == -1) {
                        $("#tableInfo").trigger('load');
                        $("#auth-info").html("Данные пользователя не были изменены. <br> Пользователь с таким логином уже зарегестрирован в системе.");
                        $("#auth-info").attr("class", "error_msg");
                    }
                    else {
                        $("#auth-info").html("Данные пользователя " + arrInput1[number].val() + " были успешно изменены <br> &nbsp;");
                        $("#auth-info").attr("class", "good_msg");
                    }
                },
                error: function (e) {
                    alert("Произошла ошибка ajax запроса!");
                }
            });
        }
        else {
            $("#auth-info").html("Данные не были изменены. <br> Пожалуйста, заполните все поля!");
            $("#auth-info").attr("class", "error_msg");
            $("#tableInfo").trigger('load');
        }
    });

    $(document).delegate(".keyType", "change", function() {
        var value = $(this).val();
        var number = $(this).data('number');
        if (value == 1)
        {
            arrSelect1[number].attr('disabled', 'disabled');
        }
        else if (value == 2)
        {
            arrSelect1[number].removeAttr('disabled');
        }
    });

    $("#addType").change(function() {
        var value = $("#addType").val();
        if (value == 1)
        {
            $(addLevel).attr('disabled', 'disabled');
        }
        else if (value == 2)
        {
            $(addLevel).removeAttr('disabled');
        }
    });


    $("#tableInfo").load(function(){
        arrTR = [];
        arrInput1 = [];
        arrInput2 = [];
        arrSelect1 = [];
        arrSelect2 = [];
        $('input[name="textfieldNewUser"]').val("");
        $('input[name="textfieldNUPassword"]').val("");
        $.ajax
        ({
            url: 'GetUsers',
            success:function(serverData)
            {
                var count = serverData.count;
                arrLogin = serverData.login.split(';')
                arrPassword = serverData.password.split(';')
                arrLevel = serverData.level.split(';')
                arrRole = serverData.role.split(';')
                arrId = serverData.id.split(';')

                var prevUser = $("#usersTable").find('tbody').children();
                if (prevUser.length > 2)
                {
                    for (var i = 2; i < prevUser.length; i++)
                    {
                        prevUser[i].remove();
                    }
                }

                for (var i = 0; i < count; i++) {
                    var body = $("#usersTable").find('tbody');
                    var tr = $('<tr>');
                    tr.attr('value', 'test');
                    var td1 = $('<td>');
                    var input1 = $('<input>');
                    input1.attr('type', 'text');
                    input1.attr('name', 'textfield');
                    input1.attr('value', arrLogin[i]);
                    arrInput1.push(input1);
                    td1.append(input1);
                    tr.append(td1);

                    var td2 = $('<td>');
                    var input2 = $('<input>');
                    input2.attr('type', 'text');
                    input2.attr('name', 'textfield');
                    input2.attr('value', arrPassword[i]);
                    arrInput2.push(input2);
                    td2.append(input2);
                    tr.append(td2);

                    var td3 = $('<td>');
                    var select1 = $('<select>');
                    var lev = arrLevel[i];
                    var type = arrRole[i];

                    select1.attr('name', 'keyLevel');
                    select1.attr('class', 'keyLevel');
                    var option1 = $('<option>');
                    option1.text('Лёгкий')
                    option1.val(1);
                    var option2 = $('<option>');
                    option2.text('Средний')
                    option2.val(2);
                    var option3 = $('<option>');
                    option3.text('Сложный')
                    option3.val(3);
                    var option4 = $('<option>');
                    option4.text('Профессионал');
                    option4.val(4);
                    if (lev == 1) {option1.attr('selected', 'selected');}
                    else if (lev == 2) {option2.attr('selected', 'selected');}
                    else if (lev ==3) {option3.attr('selected', 'selected');}
                    else if (lev == 4) {option4.attr('selected', 'selected');}
                    select1.append(option1);
                    select1.append(option2);
                    select1.append(option3);
                    select1.append(option4)
                    arrSelect1.push(select1);

                    if(type == 1)
                    {
                        select1.attr('disabled', 'disabled');
                    }
                    td3.append(select1);

                    tr.append(td3);

                    var td4 = $('<td>');
                    var select2 = $('<select>');
                    select2.attr('name', 'keyType');
                    select2.attr('class', 'keyType');
                    select2.data('number' ,i);
                    var option5 = $('<option>');
                    option5.text('Администратор');
                    option5.val(1);
                    var option6 = $('<option>');
                    option6.text('Обучаемый');
                    option6.val(2);
                    if (type == 1) {option5.attr('selected', 'selected')}
                    else if (type == 2) {option6.attr('selected', 'selected')}
                    select2.append(option5);
                    select2.append(option6);
                    arrSelect2.push(select2);
                    td4.append(select2);
                    tr.append(td4);

                    var td5 = $('<td>');
                    td5.attr('name', 'states');
                    td5.attr('class', 'usersButton');
                    var input10 = $('<input>');
                    input10.attr('type', 'button');
                    input10.attr('class', 'stateButton');
                    input10.attr('value', 'статистика');
                    input10.data('userId', arrId[i]);
                    td5.append(input10);
                    tr.append(td5);

                    var td6 = $('<td>');
                    td6.attr('class', 'usersButton');
                    var input3 = $('<input>');
                    input3.attr('type', 'button');
                    input3.attr('class', 'changeButton');
                    input3.attr('value', 'изменить');
                    input3.data('number', i);
                    input3.data('userId', arrId[i]);
                    td6.append(input3);
                    tr.append(td6);
                    body.append(tr);

                    var td7 = $('<td>');
                    td7.attr('class', 'usersButton');
                    if (arrId[i] != 1)
                    {
                        var input4 = $('<input>');
                        input4.attr('type', 'button');
                        input4.attr('class', 'deleteButton');
                        input4.attr('value', 'удалить');
                        input4.data('userId', arrId[i]);
                        input4.data('number', i);
                        td7.append(input4);
                    }
                    tr.append(td7);
                    body.append(tr);
                    arrTR.push(tr);
                }
                $('#addType').trigger('change');
            },
            error: function(e)
            {
                alert("Произошла ошибка ajax запроса!");
            }
        });
    });

    $(window).load(function(){
        $("#tableInfo").trigger('load');
    });
});