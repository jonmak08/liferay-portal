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

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
	}
);

$(function() {
	var winW = $(window).width();
	var nav = $('.horizontal-nav');
	var $w = $(window);
	var controlMenu = $('.control-menu');
	if (winW > 875) {
		var isFixed = false;
		$w.scroll(function() {
			var scrollTop = $w.scrollTop();
			var shouldBeFixed = scrollTop > 0; 
			//Set the horizontal-nav style
			if (shouldBeFixed && !isFixed) {
				//User has scrolled down
				//Navbar should be fixed, not absolute
				nav.addClass('horizontal-nav-should-be-fixed');
				nav.removeClass('horizontal-nav-should-not-be-fixed');			
				//Set the horizontal-nav top based on login status
				if (controlMenu.length) {
					//Logged in, so need to include fixed offset
					nav.addClass('horizontal-nav-offset-on');
					nav.removeClass('horizontal-nav-offset-off');
				}
				else {
					//Not logged in, so do not include offset
					nav.addClass('horizontal-nav-offset-off');
					nav.removeClass('horizintal-nav-offset-on');
				}
				isFixed = true;
			}
			else if (!shouldBeFixed && isFixed) {
				//User is at top of page
				//Navbar should be absolute, not fixed
				nav.addClass('horizontal-nav-should-not-be-fixed');
				nav.removeClass('horizontal-nav-should-be-fixed');
				//It'll take care of itself so no offset necessary
				nav.addClass('horizontal-nav-offset-off');	
				nav.removeClass('horizontal-nav-offset-on');
				isFixed = false;
			}
		});
	}
});

