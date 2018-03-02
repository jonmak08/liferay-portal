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

		$( document ).ready(() => {
			function openNav() {
				let controlMenuHeight = $('.control-menu').css('height') ? $('.control-menu').css('height') : 0;
	
				$('.modaly').css('top', controlMenuHeight);
				slideNavCheck();
				$('.bars-nav-icon').hide();
				$('#navigation ul').show();
			}
	
			function closeNav() {
				$('#navigation').css('width', '0');
				$('.bars-nav-icon').show();
				$('#navigation ul').hide();
			}
	
			let $nav = $('#navigation');
			$('#banner').append('<div class="bars-nav-icon"><i class="icon-align-justify"></i></div>');
			$nav.addClass('modaly');
			$nav.append('<a href="javascript:void(0)" class="closebtn">&times;</a>');
			$('#navigation > ul').addClass('modaly-content');
			$('.bars-nav-icon').click(openNav);
			$('.closebtn').click(closeNav);
			if (Liferay.ThemeDisplay.isSignedIn()) {
				$('.bars-nav-icon').show();
			}
			else {
				$('.bars-nav-icon').hide();
			}
	
			function slideNavCheck() {
				if (window.innerWidth < 600) {
					$('#navigation').css('width', '100%');
				}
				else if (window.innerWidth >= 600) {
					$('#navigation').css('width', '30%');
				}
			}
	
			$(window).resize(() => {
				let modalyPresent = Number($('#navigation').css('width').slice(0, -2)) > 0;
				if (modalyPresent) {
					slideNavCheck();
				}
	
				controlMenuCheck();
			});
	
			function controlMenuCheck() {
				let $controlMenuHeight = $('.control-menu').css('height') ? $('.control-menu').css('height') - 1 : 0;
	
				$('.has-control-menu #banner').css('top', $controlMenuHeight);
				$('.has-control-menu #navigation').css('top', $controlMenuHeight);
			}
	
			controlMenuCheck();

			$('#navigation > ul > li > i').click(function(event) {
				let childMenu = $(this).siblings('ul').children('li').children('a');
				if(childMenu.css('display') === 'none' || childMenu.css('display') === '') {
					$(this).siblings('a').css('color', '#FFA500');
					childMenu.css('display', 'block');
					childMenu.siblings('.icon-chevron-right').length ? childMenu.siblings('.icon-chevron-right').attr('style', 'display: block') : '';
					$(this).attr('style', 'display: none');
					$(this).siblings('.icon-chevron-up').attr(
						'style', 'display: block');
				}
				else {
					$(this).siblings('a').css('color', '#FFFFF0');
					childMenu.attr('style', 'display: none');
					childMenu.siblings('.icon-chevron-right').length ? childMenu.siblings('.icon-chevron-right').attr('style', 'display: none') : '';
					let grandChildren = childMenu.siblings('ul').children('li').children('a');
					if(grandChildren.css('display') === 'block') {
						grandChildren.attr('style', 'display: none');
						childMenu.siblings('.icon-chevron-up').attr('style', 'display: none');
						childMenu.siblings('.icon-chevron-right').attr('style', 'display: none');
					}
					$(this).attr('style', 'display: none');
					$(this).siblings('.icon-chevron-right').attr('style', 'display: block');
				}

			});

			$('#navigation > ul > li > ul > li > i').click(function(event) {
				let grandChildMenu = $(this).siblings('ul').children('li').children('a');
				if(grandChildMenu.css('display') === 'none' || grandChildMenu.css('display') === '') {
					$(this).siblings('a').css('color', '#FFA500');
					grandChildMenu.css('display', 'block');
					grandChildMenu.siblings('.icon-chevron-right').length ? grandChildMenu.siblings('.icon-chevron-up').attr('style', 'display: block') : '';
					$(this).attr('style', 'display: none');
					$(this).siblings('.icon-chevron-up').attr(
						'style', 'display: block');
				}
				else {
					$(this).siblings('a').css('color', '#FFFFF0');
					grandChildMenu.css('display', 'none');
					grandChildMenu.siblings('.icon-chevron-right').length ? grandChildMenu.siblings('.icon-chevron-right').css('display', 'none') : '';
					$(this).attr('style', 'display: none');
					$(this).siblings('.icon-chevron-right').attr('style', 'display: block');;
				}
			});
		});
	}

);