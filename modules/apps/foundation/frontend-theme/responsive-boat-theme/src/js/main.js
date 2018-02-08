
// This function gets loaded when everything, including the portlets, is on the page

Liferay.on('allPortletsReady', myPageJS);

// Function Containing All of My Event Handlers

function myPageJS() {
	let nav = $('#navigation');
	let navBtnDiv = $('#navigation div.nav-btn');
	let portletParagraphs = $('#content .portlet-body p');

	// SCROLL EVENT HANDLER

	document.onscroll = function() {

		// Toggle 'scrolled' class on nav based on scrolled location

		if (window.scrollY >= 140)
			nav.hasClass('scrolled') ? '' : nav.addClass('scrolled');
		else if (nav.hasClass('scrolled'))
			nav.removeClass('scrolled');

		// Toggle 'visible' class to make portlet content visible when scrolled to

		for (let i = 0; i < portletParagraphs.length; i++) {
			if (!portletParagraphs.eq(i).hasClass('visible') && portletParagraphs[i].getBoundingClientRect().top - window.innerHeight <= 0)
				portletParagraphs.eq(i).addClass('visible');
		}
	};

	// CLICK EVENT HANDLER

	navBtnDiv[0].onclick = function() {
		let portletDiv = $('#navigation .portlet');

		// Change maximum height of nav on click to reveal or hide nav links
		
		nav.toggleClass('expand');
		portletDiv.slideToggle(700, function() {
			portletDiv.css('display', portletDiv.css('display').replace('none',''));
		});
	};
}