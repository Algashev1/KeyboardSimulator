function visibleKeyboard()// JavaScript Document
{
	"use strict";
	var cont = document.getElementById("container");
	var chbox = document.getElementById("checkboxKeyboard");
	var divMask = document.createElement("div");
	divMask.id="mask";
		if (chbox.checked)
			{
				cont.removeChild(document.getElementById("mask"));
			}
		else 
			{
				cont.appendChild(divMask);
			}
}