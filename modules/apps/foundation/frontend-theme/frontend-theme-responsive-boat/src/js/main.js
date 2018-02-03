/* This function gets loaded when all the HTML, not including the portlets, is loaded. */
// AUI().ready(function() {});

/*	This function gets loaded after each and every portlet on the page.
	portletId: the current portlet's id
	node: the Alloy Node object of the current portlet
*/
// Liferay.Portlet.ready(function(portletId, node) {});

/* This function gets loaded when everything, including the portlets, is on the page. */
Liferay.on('allPortletsReady', myPageJS);


function myPageJS() {
	let navBar = document.getElementById('navigation'),
		navBtn = document.querySelector('#navigation div.nav-btn');

	document.addEventListener('scroll', function() {

		if (window.scrollY >= 140 && window.innerWidth > 770) {
			navBar.classList.contains('scrolled') ? '' : navBar.classList.add('scrolled');
		} else if (navBar.classList.contains('scrolled')){
			navBar.classList.remove('scrolled');
		}
	});

	navBtn.onclick = function() {
		let portlet = navBar.querySelector('.portlet'),
			style = navBar.style;

		navBar.classList.toggle('expand');
		style.maxHeight = style.maxHeight || portlet.offsetTop + 'px';
		style.maxHeight = !(Number(style.maxHeight.replace(/px/,'')) - portlet.offsetTop) * portlet.offsetHeight + portlet.offsetTop + 'px';
	};
}