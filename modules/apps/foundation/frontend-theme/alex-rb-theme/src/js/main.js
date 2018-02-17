// This function gets loaded when everything, including the portlets, is on the page
Liferay.on('allPortletsReady', myPageJS);

function myPageJS() {
	let nav = AUI.$('#navigation');
	let navBtnDiv = nav.find('div.nav-btn');

	let portletArticleElements = AUI.$('#content .journal-content-article > *');
	let visibleArticleParents = portletArticleElements.parent().closest('.visible');
	let visibleArticleElements = visibleArticleParents.find('.journal-content-article > *');

	// Eliminate portlet article elements that are already visible
	portletArticleElements = portletArticleElements.not(visibleArticleElements);

	document.onscroll = _.throttle(
		function() {
			let hasScrolled = window.scrollY < 140;

			// Toggle 'scrolled' class on nav and heading based on scroll location
			nav.toggleClass('scrolled', !hasScrolled);

			// Add 'visible' class to make portlet content visible when scrolled to
			for (let i = 0; i < portletArticleElements.length; i++) {
				let topLevelChild = portletArticleElements.eq(i);

				let scrollPositionY = topLevelChild.get(0).getBoundingClientRect().top;

				let hasClassVisible = scrollPositionY < window.innerHeight;

				if (hasClassVisible) {
					topLevelChild.addClass('visible');

					// Remove element from jQuery object so that it's no longer tracked, and to reduce calculations
					portletArticleElements = portletArticleElements.not(topLevelChild);
				}
			}
		},
		200
	);

	navBtnDiv.get(0).onclick = function() {
		let portletDiv = nav.find('.portlet');

		$(this).toggleClass('expand');

		portletDiv.slideToggle(
			300,
			function() {
				portletDiv.css('display', portletDiv.css('display').replace('none', ''));
			}
		);
	};
}