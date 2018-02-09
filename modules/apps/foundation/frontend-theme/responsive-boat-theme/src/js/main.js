// This function gets loaded when everything, including the portlets, is on the page
Liferay.on('allPortletsReady', myPageJS);

function myPageJS() {
	let nav = AUI.$('#navigation');
	let navBtnDiv = nav.find('div.nav-btn');
	let portletParagraphs = AUI.$('#content .portlet-body p');

	document.onscroll = function() {
		// Toggle 'scrolled' class on nav based on scrolled location
		let winScrollY = window.scrollY;

		let hasScrolled = winScrollY < 140;

		nav.toggleClass('scrolled', !hasScrolled);

		// Toggle 'visible' class to make portlet content visible when scrolled to
		for (let i = 0; i < portletParagraphs.length; i++) {
			let hasCssVisible = portletParagraphs.eq(i).hasClass('visible');
			let scrollPositionY = portletParagraphs[i].getBoundingClientRect().top;

			let isVisible = hasCssVisible || scrollPositionY < window.innerHeight;

			portletParagraphs.eq(i).toggleClass('visible', isVisible);
		}
	};

	navBtnDiv[0].onclick = function() {
		let portletDiv = nav.find('.portlet');

		nav.toggleClass('expand');

		portletDiv.slideToggle(
			700,
			function() {
				portletDiv.css('display', portletDiv.css('display').replace('none',''));
			}
		);
	};
}