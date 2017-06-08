AUI().ready(
	'liferay-sign-in-modal',
	'scroll-transparency',
	function(A) {
		A.on(
			'scroll',
			function() {
				var banner = AUI.$('#banner');
				var buttonScrollTop = AUI.$('.btn-scroll-top');

				var scrollY = window.pageYOffset;

				if (scrollY > 350 && banner.hasClass('banner-unscrolled')) {
					banner.toggleClass('banner-scrolled banner-unscrolled');

					buttonScrollTop.fadeIn();
				}
				else if (scrollY <= 350 && banner.hasClass('banner-scrolled')) {
					banner.toggleClass('banner-scrolled banner-unscrolled');

					buttonScrollTop.fadeOut();
				}
			}
		);

		var signIn = A.one('#signIn');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	}
);

function toggleOpenClose() {
	var currentTarget = AUI.$(this);

	var currentTargetSibling = currentTarget.siblings('ul.child-menu');

	if (currentTargetSibling) {
		currentTargetSibling.toggleClass('child-menu-open');
		currentTarget.toggleClass('child-menu-toggle-open');
	}
}

AUI.$('a.theme-nav-bar-toggle').click(
	function() {
		AUI.$('#themeNavBar').toggleClass('menu-open');
	}
);

AUI.$('.child-menu-dropdown-1', '.child-menu-dropdown-2').click(
	toggleOpenClose
);

AUI.$('.btn-scroll-top').click(
	function() {
		AUI.$('html, body').animate(
			{
				scrollTop: 0
			},
			700
		);

		return false;
	}
);