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

	function() {
	}
);

Liferay.on(
	'allPortletsReady',

	/*
	This function gets loaded when everything, including the portlets, is on
	the page.
	*/

	function() {
		let $ = AUI.$;
		$(document).ready(
			function() {
				function openNav() {
					let $modaly = $('.modaly');

					$modaly.addClass('nav-show');
					$modaly.removeClass('nav-noshow');

					$('.bars-nav-icon').hide(1000);
				}

				function closeNav() {
					let $modaly = $('.modaly');

					$modaly.addClass('nav-noshow');
					$modaly.removeClass('nav-show');

					$('.bars-nav-icon').show(1000);
				}

				function toggleHamburger(bool) {
					let $hamburger = $('.bars-nav-icon');

					$hamburger.hide();

					if (bool) {
						$hamburger.show();
					}
				}

				function controlMenuCheck() {
					let $controlMenuHeight = $('.control-menu').height() ? $('.control-menu').height() - 2 : 0;

					$('.has-control-menu #banner').css('top', $controlMenuHeight + 'px');
					$('.has-control-menu #navigation').css('top', $controlMenuHeight + 'px');
				}

				$('.bars-nav-icon').click(openNav);
				$('.close-btn').click(closeNav);

				toggleHamburger(Liferay.ThemeDisplay.isSignedIn());

				controlMenuCheck();

				$(window).resize(controlMenuCheck);

				$('#navigation > ul > li > i').click(
					function(e) {
						e.preventDefault();
						let childMenu = $(this).siblings('ul').children('li').children('a');
						let grandChildren = childMenu.siblings('ul').children('li').children('a');

						let action = 'slideUp';
						let color = '#FFFFF0';
						let currentArrow = '.icon-chevron-right';
						let toggleChildRight = 'hide';

						if (childMenu.css('display') === 'none' || childMenu.css('display') === '') {
							if (childMenu.siblings('.icon-chevron-right').length) {
								toggleChildRight = 'show';
							}

							currentArrow = '.icon-chevron-up';
							action = 'slideDown';
							color = '#FFA500';
						}

						if (grandChildren.css('display') === 'block') {
							grandChildren.hide();
							childMenu.siblings('.icon-chevron-up').hide();
							childMenu.siblings('.icon-chevron-right').hide();
						}

						$(this).siblings(currentArrow).show();

						childMenu.siblings('.icon-chevron-right')[toggleChildRight]();

						childMenu[action]();

						$(this).siblings('a').css('color', color);

						$(this).hide();
					}
				);

				$('#navigation > ul > li > ul > li > i').click(
					function(e) {
						e.preventDefault();
						let grandChildMenu = $(this).siblings('ul').children('li').children('a');

						let action = 'slideUp';
						let color = '#FFFFF0';
						let currentArrow = '.icon-chevron-right';
						let toggleChildRight = 'hide';

						if (grandChildMenu.css('display') === 'none' || grandChildMenu.css('display') === '') {
							if (grandChildMenu.siblings('.icon-chevron-right').length) {
								toggleChildRight = 'show';
							}

							currentArrow = '.icon-chevron-up';
							action = 'slideDown';
							color = '#FFA500';
						}

						$(this).siblings(currentArrow).show();

						grandChildMenu.siblings('.icon-chevron-right')[toggleChildRight]();

						grandChildMenu[action]();

						$(this).siblings('a').css('color', color);

						$(this).hide();
					}
				);
			}
		);
	}
);