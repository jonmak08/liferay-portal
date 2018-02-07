AUI().ready(
	hideHeadingScroll()
);

Liferay.Portlet.ready(
	function(portletId, node) {
	}
);

Liferay.on(
	'allPortletsReady',
	function() {
	}
);

function hideHeadingScroll() {
	var DELTA = 40;

	var HEADERTHRESHOLD = 300;

	var lastScrollTop = 0;
	var scrolling;

	var banner = AUI.$('#banner');
	var navbarCollapse = AUI.$('.navbar-collapse');
	var navbarSupportedContent = AUI.$('#navbarSupportedContent');
	var navbarToggle = AUI.$('.navbar-toggle');
	var ulDropdown = AUI.$('ul.dropdown-menu [data-toggle=dropdown]');

	var navbarHeight = banner.outerHeight();

	AUI.$(window).scroll(
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
		var scrollTop = AUI.$(this).scrollTop();

		var columnOne = AUI.$('#column-1');
		var content = AUI.$('#content');
		var portletColumnOne = AUI.$('#column-1 .portlet-content');

		if (portletColumnOne.find('img').length == 0) {
			columnOne.css('padding', '20%');

			banner.addClass('lower-position').removeClass('higher-position');
		}
		else {
			columnOne.css('padding', '0');
		}

		if (scrollTop < HEADERTHRESHOLD && content.find('.category').length == 0 ) {
			banner.removeClass('lower-position').addClass('higher-position');
		}
		else if (banner.hasClass('higher-position') ) {
			banner.removeClass('higher-position').addClass('lower-position');
		}

		if (Math.abs(lastScrollTop - scrollTop) > DELTA) {
			if (scrollTop > lastScrollTop && scrollTop > navbarHeight) {
				banner.removeClass('nav-down').addClass('nav-up');

				if (navbarSupportedContent.hasClass('navbar-collapse collapse in') ) {
					navbarCollapse.removeClass('in');

					navbarCollapse.attr('aria-expanded', 'false');

					navbarToggle.addClass('collapsed');

					navbarToggle.attr('aria-expanded', 'false');
				}
			}
			else if (scrollTop + AUI.$(window).height() < AUI.$(document).height() ) {
				banner.removeClass('nav-up').addClass('nav-down');
			}

			lastScrollTop = scrollTop;
		}
	}

	ulDropdown.on(
		'click',
		function(event) {
			event.preventDefault();

			event.stopPropagation();

			ulDropdown.parent().removeClass('open');

			AUI.$(this).parent().addClass('open');
		}
	);
}