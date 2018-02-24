// This function gets loaded when everything, including the portlets, is on the page
Liferay.on('allPortletsReady', myPageJS);

function myPageJS() {
	let nav = AUI.$('#navigation');

	let childDropdownBtns = nav.find('.child-menu span.icon-angle-down');
	let navBtnDiv = nav.find('div.nav-btn');
	let parentDropdownBtns = nav.find('> ul > li > span.icon-angle-down');

	/*
		Define max-height of nav so that all overflowing links can be seen and scrolled to
		on a mobile device.
	*/

	function handleNavOverflow() {
		let navUl = nav.find('.nav');

		let navOffsetTop = navUl.offset().top || nav.outerHeight() + nav.offset().top;

		let maxNavUlHeight = window.innerHeight - navOffsetTop;

		navUl.css('max-height', maxNavUlHeight);
	}

	handleNavOverflow();

	AUI.$(window).resize(AUI._.debounce(handleNavOverflow, 150));

	function handleDropdownClick(target) {
		let siblingUl = AUI.$(target).find('+ ul');

		AUI.$(target).toggleClass('expand');

		siblingUl.slideToggle(
			300,
			function() {
				AUI.$(this).css('display', AUI.$(this).css('display').replace('none', ''));
			}
		);
	}

	childDropdownBtns.each(
		function() {
			this.onclick = function() {
				handleDropdownClick(this);
			};
		}
	);

	navBtnDiv.get(0).onclick = function() {
		handleDropdownClick(this);
	};

	parentDropdownBtns.each(
		function() {
			this.onclick = function() {
				if (window.innerWidth < 768) {
					handleDropdownClick(this);
				}
			};
		}
	);
}