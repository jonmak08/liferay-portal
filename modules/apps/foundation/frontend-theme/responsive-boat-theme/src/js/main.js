// This function gets loaded when everything, including the portlets, is on the page
Liferay.on('allPortletsReady', myPageJS);

function myPageJS() {
	let nav = AUI.$('#navigation');
	let navBtnDiv = nav.find('div.nav-btn');
	let portletParagraphs = AUI.$('#content .portlet-body p');

	document.onscroll = _.throttle(function() {
		// Toggle 'scrolled' class on nav based on scrolled location
		let hasScrolled = window.scrollY < 140;

		nav.toggleClass('scrolled', !hasScrolled);

		// Add 'visible' class to make portlet content visible when scrolled to
		for (let i = 0; i < portletParagraphs.length; i++) {
			let paragraph = portletParagraphs.eq(i);

			let scrollPositionY = paragraph.get(0).getBoundingClientRect().top;

			let isVisible = scrollPositionY < window.innerHeight;

			if (isVisible) {
				paragraph.addClass('visible');

				// Remove element from jQuery object so that it's no longer tracked, and to reduce calculations
				portletParagraphs = portletParagraphs.not(paragraph);
			}
		}
	}, 200);

	navBtnDiv.get(0).onclick = function() {
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