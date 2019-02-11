AUI().ready(
	'liferay-sign-in-modal',
	function(A) {
		var signIn = A.one('.sign-in > a');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	}
);

(function() {
	document.querySelector('.fa-angle-down').addEventListener(
		'click',
		function() {
			document.querySelector('.child-toggle').classList.toggle('nav-closed');
		},
		false);
})();

(function() {
	document.querySelector('.grandchild-arrow').addEventListener(
		'click',
		function() {
			document.querySelector('.grandchild-toggle').classList.toggle('nav-closed');
		}, false);
})();

function stickyNav() {
	var pageYOffset = window.pageYOffset;

	var themeNavigationBar = document.getElementById('banner');

	var sticky = themeNavigationBar.offsetTop;

	var offsetDifference = pageYOffset > sticky;

	var element = document.getElementById('_com_liferay_product_navigation_product_menu_web_portlet_ProductMenuPortlet_sidenavSliderId');

	if (offsetDifference && element.offsetWidth === 0) {
		themeNavigationBar.classList.add('sticky');
	}
	else if (offsetDifference && element.offsetWidth === 320) {
		themeNavigationBar.classList.add('sticky-menu-open');
	}
	else {
		themeNavigationBar.classList.remove('sticky', 'sticky-menu-open');
	}
}

window.onscroll = function() {
	stickyNav();
};

var footerCount = document.getElementById('footer-count');

if (footerCount) {
	var customListItems = footerCount.getElementsByTagName('li');

	if (customListItems.length > 4) {
		footerCount.classList.add('my-wrap');
	}
}

YUI().use(
	'aui-tooltip',
	function(Y) {
		new Y.TooltipDelegate(
			{
				position: 'bottom',
				trigger: '#footer-count li'
			}
		);
	}
);