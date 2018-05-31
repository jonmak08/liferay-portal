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

function revealSearchBar() {
	$("#nav-menu-hamburger-icon").toggle();
	$("nav#navigation li").toggle();
	$("#nav-menu-search-icon2").toggle();
	$("#nav-menu-search-icon2 h2").hide();
	$("#nav-menu-search-icon2 svg").hide();
	$("#nav-menu-search-icon2 input").attr("placeholder", "Type & hit Enter...");
}