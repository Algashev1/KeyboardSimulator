window.onload = chooseByKeyType();

function chooseByKeyType()
{
	"use strict";
	var tr = document.getElementById("usersTable").getElementsByTagName('tr').length;
	for (var i=0; i<tr-2; i++)
		{
			var statesI = document.getElementById("states"+i);
			
			var checkboxI = document.getElementById("checkbox"+i);
			var keyTypeI = document.getElementById("keyType"+i);
			var n = keyTypeI.options.selectedIndex;
			if (keyTypeI.options[n].value === "user")
				{
					statesI.innerHTML = '<div class="usersButton"><input type="button" name="button2" id="button2" value="Посмотреть" onClick="statesPage()"></div>';
					checkboxI.checked = true;
					checkboxI.disabled=true;
				}
			else if (keyTypeI.options[n].value === "admin")
				{
					statesI.innerHTML = '';
					checkboxI.checked = false;
					checkboxI.disabled=true;
				}
		}
}