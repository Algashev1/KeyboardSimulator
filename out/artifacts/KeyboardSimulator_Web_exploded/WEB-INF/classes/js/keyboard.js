$(function(){
	"use strict";
	var $write = $('#write'),
		shift = false,
		capslock = false;	
	
	 $('#write').keydown(function( event ){ // задаем функцию при нажатиии любой клавиши клавиатуры на элементе
		$('#keyboard li').click(function(){
		var $this = $(this),
			character = $this.html(); // If it's a lowercase letter, nothing happens to this variable
 
		// Shift keys
		if ($this.hasClass('left-shift') || $this.hasClass('right-shift')) {
			$('.letter').toggleClass('uppercase');
			$('.symbol span').toggle();
 
			shift = (shift === true) ? false : true;
			capslock = false;
			return false;
		}
 
		// Caps lock
		if ($this.hasClass('capslock')) {
			$('.letter').toggleClass('uppercase');
			capslock = true;
			return false;
		}
 
		// Delete
		if ($this.hasClass('delete')) {
			var html = $write.html();
 
			$write.html(html.substr(0, html.length - 1));
			return false;
		}
 
		// Special characters
		if ($this.hasClass('symbol')) character = $('span:visible', $this).html();
		if ($this.hasClass('space')) character = ' ';
		if ($this.hasClass('tab')) character = "	";
		if ($this.hasClass('return')) character = "";
 
		// Uppercase letter
		if ($this.hasClass('uppercase')) character = character.toUpperCase();
 
		// Remove shift once a key is clicked.
		if (shift === true) {
			$('.symbol span').toggle();
			if (capslock === false) $('.letter').toggleClass('uppercase');
 
			shift = false;
		}
 
		// Add the character
		$write.html($write.html() + character);
	});
	    switch( event.which )
			{
			case 192:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 49:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 50:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 51:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 52:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 53:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 54:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 55:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 56:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 57:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 48:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 189:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 187:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 8:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 9:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 81:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 87:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 69:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 82:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 84:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 89:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 85:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 73:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 79:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 80:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 219:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 221:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 220:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 20:
				$("#"+event.which).css( "background", "#FFFF33" );
				$('.letter').toggleClass('uppercase');
				break;
			case 65:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 83:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 68:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 70:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 71:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 72:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 74:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 75:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 76:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 186:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 222:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 13:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 90:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 88:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 67:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 86:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 66:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 78:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 77:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 188:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 190:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 191:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 32:
				$("#"+event.which).css( "background", "#FFFF33" );
				break;
			case 16:
				$("."+event.which).css( "background", "#FFFF33" );
				$('.letter').toggleClass('uppercase');
				$('.symbol span').toggle();
				break;
			}
	 
		 // выводим код нажатой клавиши
	  });
	
	$( "body" ).keyup(function( event ){ // задаем функцию при нажатиии любой клавиши клавиатуры на элементе
	    switch( event.which )
			{
			case 192:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 49:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 50:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 51:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 52:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 53:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 54:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 55:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 56:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 57:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 48:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 189:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 187:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 8:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 9:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 81:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 87:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 69:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 82:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 84:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 89:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 85:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 73:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 79:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 80:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 219:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 221:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 220:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 20:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 65:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 83:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 68:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 70:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 71:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 72:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 74:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 75:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 76:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 186:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 222:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 13:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 90:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 88:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 67:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 86:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 66:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 78:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 77:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 188:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 190:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 191:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 32:
				$("#"+event.which).css( "background", "#FFFFFF" );
				break;
			case 16:
				$("."+event.which).css( "background", "#FFFFFF" );
				$('.letter').toggleClass('uppercase');
				$('.symbol span').toggle();
				break;
			}
	 
		 // выводим код нажатой клавиши
	  });
 
	
});// JavaScript Document