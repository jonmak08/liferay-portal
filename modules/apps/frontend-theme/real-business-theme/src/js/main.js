AUI().ready(
	'liferay-sign-in-modal',
	function(A) {
		var signIn = A.one('.sign-in > a');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	}
);

// STICKY NAV

function stickyNav() {
	const themeNavigationBar = document.getElementById('banner');

	const sticky = themeNavigationBar.offsetTop;

	let elmnt = document.getElementById('_com_liferay_product_navigation_product_menu_web_portlet_ProductMenuPortlet_sidenavSliderId');

	if (window.pageYOffset > sticky && elmnt.offsetWidth === 0) {
		themeNavigationBar.classList.add('sticky');
	}
	else if (window.pageYOffset > sticky && elmnt.offsetWidth === 320) {
		themeNavigationBar.classList.add('sticky-menu-open');
	}
	else {
		themeNavigationBar.classList.remove('sticky', 'sticky-menu-open');
	}
}

window.onscroll = function() {
	stickyNav();
};

// STICKY NAV END

// BREAK FUNCTION

var customUnorderedList = document.getElementById('footer-count');

var customListItems = customUnorderedList.getElementsByTagName('li');

if (customListItems.length > 4) {
	customUnorderedList.classList.add('my-wrap');
}

// BREAK FUNCTION END

// ALLOY TOOLTIP

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

// ALLOY TOOLTIP END