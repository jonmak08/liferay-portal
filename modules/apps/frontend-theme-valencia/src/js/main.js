AUI().ready(

	/*
	This function gets loaded when all the HTML, not including the portlets, is
	loaded.
	*/

	function() {

		$('.carousel').carousel();

		$('.dropdown-toggle').dropdown('toggle');

		var transparent = true;

		if ($('.navbar-color-on-scroll').length !== 0) {
			$(window).on( 'scroll', debounce(function () {
				if (!transparent) {
					transparent = true;

					$('.navbar-color-on-scroll').addClass('navbar-transparent');
				}


				if ($(document).scrollTop() > 100 && transparent) {
					transparent = false;

					$('.navbar-color-on-scroll').removeClass('navbar-transparent');
				}
			}, 17));
		}

		function debounce(func, wait, immediate) {
			var timeout;

			return function () {
				var args = arguments;
				var instance = this;

				clearTimeout(timeout);

				timeout = setTimeout(
					function() {
						timeout = null;

						if (!immediate) {
							func.apply(instance, args);
						}
					}, wait)
				;

				if (immediate && !timeout) {
					func.apply(instance, args);
				}
			};
		}
		
		if ($('#ControlMenu').hasClass('control-menu')) {
			$('#navigation').addClass('navbar-control-helper');
		}
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