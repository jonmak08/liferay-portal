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
	var banner = $('#banner');
	var lastScrollTop = 0;
	var navbarCollapse = $('.navbar-collapse');
	var navbarHeight = banner.outerHeight();
	var navbarSupportedContent = $('#navbarSupportedContent');
	var navbarToggle = $('.navbar-toggle');
	var scrolling;
	var ulDropdown = $('ul.dropdown-menu [data-toggle=dropdown]');

	var headerThreshold = 300;
	var screenSm = 768;

	$(window).scroll(
		function(e) {
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

		var controlMenuHeight = $('.control-menu').height();

		console.log(scrollTop);

		if( scrollTop < headerThreshold && banner.width() > 768) {
			banner.removeClass('lower-position').addClass('higher-position');
		
		}else if(banner.hasClass('higher-position')){
			banner.removeClass('higher-position').addClass('lower-position');
		}

		if (Math.abs(lastScrollTop - scrollTop) > delta) {
			if (scrollTop > lastScrollTop && scrollTop > navbarHeight) {
				banner.removeClass('nav-down').addClass('nav-up');

				if (navbarSupportedContent.hasClass('navbar-collapse collapse in')) {
					navbarCollapse.removeClass('in');

					navbarCollapse.attr('aria-expanded', 'false');

					navbarToggle.addClass('collapsed');
					
					navbarToggle.attr('aria-expanded', 'false');
				}
			}
			else if (scrollTop + $(window).height() < $(document).height()) {
				banner.removeClass('nav-up').addClass('nav-down');
			}

			lastScrollTop = scrollTop;
		}
	}

	ulDropdown.on('click', 
		function(e) {
			e.preventDefault(); //stop the original href 

			e.stopPropagation(); //stop menu from closing if its open

			ulDropdown.parent().removeClass('open'); //closes other menus 

			$(this).parent().addClass('open');
		}
	);
}