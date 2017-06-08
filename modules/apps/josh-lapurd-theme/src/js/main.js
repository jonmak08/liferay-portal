AUI().ready(
	'liferay-sign-in-modal',
	'scroll-transparency',
	function(A) {
		A.on(
			'scroll',
			function() {
				var banner = AUI.$('#banner');
				var buttonBtt = AUI.$('.btn-back-to-top');
				var scrollY = window.pageYOffset;

				if (scrollY > 350 && banner.hasClass('banner-unscrolled')) {
					banner.toggleClass('banner-scrolled banner-unscrolled');
					buttonBtt.fadeIn();
				}
					else if (scrollY <= 350 && banner.hasClass('banner-scrolled')) {
						banner.toggleClass('banner-scrolled banner-unscrolled');
						buttonBtt.fadeOut();
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
	var currTarget = AUI.$(this);
	var currTarSib = currTarget.siblings('ul.child-menu');

	if (currTarSib !== undefined) {
		currTarSib.toggleClass('child-menu-open');
		currTarget.toggleClass('child-menu-toggle-open');
	}
}

AUI.$('a.theme-nav-bar-toggle').click(
	function() {
		AUI.$('#themeNavBar').toggleClass('menu-open');
	}
);

AUI.$('#childMenuDropdown_1').click(
	toggleOpenClose
);

AUI.$('#childMenuDropdown_2').click(
	toggleOpenClose
);

AUI.$('.btn-back-to-top').click(
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