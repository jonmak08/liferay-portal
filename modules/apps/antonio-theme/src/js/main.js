$(document).ready(function() {
	AUI().ready(
		'liferay-sign-in-modal',
		function(A) {
			var signIn = A.one('#signIn');

			if (signIn && signIn.getData('redirect') !== 'true') {
				signIn.plug(Liferay.SignInModal);
			}
		}
	);

	var navbarStartingPosition = 0;
	var navbar = $('.navbar-color-one');
	var navbarCurrentPosition = navbar.offset().top;
	var controlMenu = $('.control-menu');
	var pageContent = $('.page-content');

	// check if page loads with liferay-portal's control menu:
	if (controlMenu.length > 0) {
		pageContent.addClass('control-menu-visible');
		navbar.addClass('refix-navbar');

		var navbarStartingPosition = controlMenu.outerHeight() + 1;
	}

	navbar.toggleClass('navbar-color-two', navbarCurrentPosition > navbarStartingPosition);

	$(document).scroll(function() {
		var navbarCurrentPosition = navbar.offset().top;

		navbar.toggleClass('navbar-color-two', navbarCurrentPosition > navbarStartingPosition);
	});

	var navItemDropdown = $('li.dropdown');

	navItemDropdown.hover(
		function() {
			$(this).children('.child-menu').show();
		}, function() {
			$(this).children('.child-menu').hide();
		}
	);

	var carouselSlideOne = $('.slide-one');
	var carouselSlideTwo = $('.slide-two');
	var carouselSlideThree = $('.slide-three');

	var pagerOne = $('.pager-one');

	pagerOne.click(function() {
		carouselSlideOne.fadeIn(500);
		carouselSlideTwo.hide();
		carouselSlideThree.hide();
	});

	var pagerTwo = $('.pager-two');

	pagerTwo.click(function() {
		carouselSlideTwo.fadeIn(500);
		carouselSlideOne.hide();
		carouselSlideThree.hide();
	});

	var pagerThree = $('.pager-three');

	pagerThree.click(function() {
		carouselSlideThree.fadeIn(500);
		carouselSlideOne.hide();
		carouselSlideTwo.hide();
	});
});
