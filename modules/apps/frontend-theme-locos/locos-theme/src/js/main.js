
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
		let $mainContent = $("#wrapper #main-content");

		//add animated class to first row of layout
		let $firstRowPortlets = $mainContent.find('> div:first-child .portlet-content');
		$firstRowPortlets.addClass('animated');
		$firstRowPortlets.css('background-color', 'linear-gradient(to bottom, $turquoise 0%, $turquoise 50%, $ivory 50%, $ivory 100%)');

		//an overlay for banner navigation
		let $nav = $('#navigation');
		$nav.prepend('<div id="bars-nav-icon"><i class="icon-align-justify"></i></div>');
		$nav.prepend('<div id="slide-nav" class="overlay"></div>');
		$('#slide-nav').append('<a href="javascript:void(0)" class="closebtn">&times;</a>');
		$('#navigation ul').addClass('overlay-content');
		$('#slide-nav').append($('#navigation ul'));
		$('#bars-nav-icon').click(openNav);
		$('.closebtn').click(closeNav);
		$('#wrapper').append("<div class='background-overlay' ></div>");
		$('.background-overlay').hide();

		function openNav() {
			let controlMenuHeight = $('.control-menu').css('height');

			$('.overlay').css('top', controlMenuHeight);
			$('#slide-nav').css('width', '30%');
			$('#bars-nav-icon').hide();
			$('#slide-nav ul').show();
			$('.background-overlay').show();
			$('.background-overlay').click(() => {
				closeNav();
			});
		}

		function closeNav() {
			$('#slide-nav').css('width', '0');
			$('#bars-nav-icon').show();
			$('#slide-nav ul').hide();
			$('.background-overlay').hide();
		}

		//when resize window 
		$( window ).resize(() => {
			let isOverlayPresent = Number($('#slide-nav').css('width').slice(0, -2)) > 0;
			//responsive overlay
			if(isOverlayPresent) {
				if (window.innerWidth < 600) {
					$('#slide-nav').css('width', '100%');
				} else if (window.innerWidth >= 600) {
					$('#slide-nav').css('width', '30%');
				}
			}
		});
	}

);