
// This function gets loaded when everything, including the portlets, is on the page

Liferay.on('allPortletsReady', myPageJS);

// Function Containing All of My Event Handlers

function myPageJS() {
	let nav = AUI.$('#navigation');
	let navBtnDiv = AUI.$('#navigation div.nav-btn');
	let portletParagraphs = AUI.$('#content .portlet-body p');

	document.onscroll = function() {
		// Toggle 'scrolled' class on nav based on scrolled location
		if (window.scrollY >= 140 && !nav.hasClass('scrolled'))
			nav.addClass('scrolled');
		else if (window.scrollY < 140 && nav.hasClass('scrolled'))
			nav.removeClass('scrolled');

		// Toggle 'visible' class to make portlet content visible when scrolled to
		for (let i = 0; i < portletParagraphs.length; i++) {
			if (!portletParagraphs.eq(i).hasClass('visible') && portletParagraphs[i].getBoundingClientRect().top - window.innerHeight <= 0)
				portletParagraphs.eq(i).addClass('visible');
		}
	};

	navBtnDiv[0].onclick = function() {
		let portletDiv = AUI.$('#navigation .portlet');

		nav.toggleClass('expand');

		portletDiv.slideToggle(
			700,
			function() {
				portletDiv.css('display', portletDiv.css('display').replace('none',''));
			}
		);
	};
}