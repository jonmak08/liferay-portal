AUI().ready(

	/* This function gets loaded when all the HTML, not including the portlets, is loaded. */

	function() {

		var delta = 10;
		var lastScrollTop = 0;
		var navbarHeight = $('#banner').outerHeight();
		var scrolling;

		$(window).scroll(
			function(event) {
				scrolling = true;
			}
		);

		setInterval( function() {
			if (scrolling) {
				moveBar();
				scrolling = false;
			}
		}, 250);

		function moveBar() {
			var st = $(this).scrollTop();

		    // Make sure they scroll more than delta

			if (Math.abs(lastScrollTop - st) <= delta) {
				return;
			}

		    // If they scrolled down and are past the navbar, add class .nav-up.

		    // This is necessary so you never see what is 'behind' the navbar.

			if (st > lastScrollTop && st > navbarHeight) {

		        // Scroll Down

				$('#banner').removeClass('nav-down').addClass('nav-up');

				if ($('#navbarSupportedContent').attr('class') == 'navbar-collapse collapse in') {
					$('.navbar-collapse').attr('class', 'navbar-collapse collapse');
					$('.navbar-collapse').attr('aria-expanded', 'false');
					$('.navbar-toggle').addClass('collapsed');
					$('.navbar-toggle').attr('aria-expanded', 'false');
				}
			}
			else {

				// Scroll Up

				if (st + $(window).height() < $(document).height()) {
					$('#banner').removeClass('nav-up').addClass('nav-down');
				}
			}

			lastScrollTop = st;
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

	/* This function gets loaded when everything, including the portlets, is on the page. */

	function() {
	}
);