/* This function gets loaded when all the HTML, not including the portlets, is loaded. */
AUI().ready(function() {}); /******************** NOT USED ********************/

/*	This function gets loaded after each and every portlet on the page.
	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
*/
Liferay.Portlet.ready(function(portletId, node) {}); /******************** NOT USED ********************/

/* This function gets loaded when everything, including the portlets, is on the page. */
Liferay.on('allPortletsReady', myPageJS); /******************** USED ********************/



// Function Containing All of My Event Handlers
function myPageJS() {
	let navBar = document.getElementById('navigation'),
		navBtn = document.querySelector('#navigation div.nav-btn'),
		portletPs = document.querySelectorAll('#content .portlet-body p');



	/********** SCROLL EVENT HANDLER **********/
	document.onscroll = function() {
		// Toggle 'scrolled' class on navbar based on scrolled location
		if (window.scrollY >= 140 && window.innerWidth > 770)
			navBar.classList.contains('scrolled') ? '' : navBar.classList.add('scrolled');
		else if (navBar.classList.contains('scrolled'))
			navBar.classList.remove('scrolled');

		// Toggle 'visible' class to make portlet content visible when scrolled to
		for (let i = 0; i < portletPs.length; i++) {
			if (!portletPs[i].classList.contains('visible') && portletPs[i].getBoundingClientRect().top - window.innerHeight <= 0)
				portletPs[i].classList.add('visible');
		}
	};
	/********** END OF SCROLL EVENT HANDLER **********/



	/********** CLICK EVENT HANDLER **********/
	navBtn.onclick = function() {
		let portlet = navBar.querySelector('.portlet'),
			style = navBar.style;

		// Change maximum height of navbar on click to reveal or hide nav links
		navBar.classList.toggle('expand');
		style.maxHeight = style.maxHeight || portlet.offsetTop + 'px';
		style.maxHeight = !(Number(style.maxHeight.replace(/px/,'')) - portlet.offsetTop) * portlet.offsetHeight + portlet.offsetTop + 'px';
	};
	/********** END OF CLICK EVENT HANDLER **********/
}