AUI().ready(

	/* This function gets loaded when all the HTML, not including the portlets, is loaded. */
	hideHeadingScroll()
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

function hideHeadingScroll() {
	var delta = 40;
	var lastScrollTop = 0;
	var navbarHeight = $('#banner').outerHeight();
	var scrolling;

	$(window).scroll(
		function(event) {
			scrolling = true;
		}
	);

	setInterval(
		function() {
				if (scrolling) {
					moveBar();
					scrolling = false;
				}
		},
		250
	);

	function moveBar() {
		var scrollTop = $(this).scrollTop();

		if (Math.abs(lastScrollTop - scrollTop) > delta) {

			if (scrollTop > lastScrollTop && scrollTop > navbarHeight) {

				if ($('#navbarSupportedContent').hasClass('navbar-collapse collapse in')) {
					$('.navbar-collapse').removeClass('in');

					$('.navbar-collapse').attr('aria-expanded', 'false');

					$('.navbar-toggle').addClass('collapsed');
					
					$('.navbar-toggle').attr('aria-expanded', 'false');
				}

				$('#banner').removeClass('nav-down').addClass('nav-up');
			}
			else if (scrollTop + $(window).height() < $(document).height()) {
				$('#banner').removeClass('nav-up').addClass('nav-down');
			}

			lastScrollTop = scrollTop;
		}
	}
}