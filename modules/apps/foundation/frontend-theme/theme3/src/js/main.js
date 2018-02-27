AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/
function() {


}
);
Liferay.Portlet.ready(

	/*
	This function gets loaded after each and every portlet on the page.

	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
	*/

	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',

	

	function() {

	
	}
);


$(document).ready(function () {
	$(window).scroll(function() {
		if($(document).scrollTop() > 450) {
    		$('#navigation').addClass('hidenav');
		}
		else {
			$('#navigation').removeClass('hidenav');
		}
	});

	$(window).scroll(function() {
		if($(document).scrollTop() > 170) {
			$('#navigation').addClass('hidemobilenav');
		}
		else {
		$('#navigation').removeClass('hidemobilenav');
		}
	});

	$('#navigation').on('click', function() {
		$('#navigation').toggleClass ('togglesnav');	
	});

	$('#navigation').on('click', function() {
		$('#navigation').toggleClass ('togglesmobilenav');
	});

	$('ul').on('click', function() {
		$('ul').toggleClass ('adddisplay');
	});

var clickCount=0;
		$('.dropdown-toggle').on('click', function (){
			clickCount++;
				if(clickCount >= 2) {
					$('[data-toggle="dropdown"]').addClass('disabled');
				};
		}); 
});