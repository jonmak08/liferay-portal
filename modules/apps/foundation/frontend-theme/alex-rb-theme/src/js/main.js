// This function gets loaded when everything, including the portlets, is on the page
Liferay.on('allPortletsReady', myPageJS);

function myPageJS() {
	let $hiddenArticleGroups = AUI.$('.portlet-fade .action-group, .portlet-push .action-group');
	let $nav = AUI.$('#navigation');

	let $childDropdownBtns = $nav.find('.child-menu span.icon-angle-down');
	let $navBtnDiv = $nav.find('div.nav-btn');
	let $parentDropdownBtns = $nav.find('> ul > li > span.icon-angle-down');

	function handleDropdownClick(target) {
		let $siblingUl = AUI.$(target).find('+ ul');

		AUI.$(target).toggleClass('expand');

		$siblingUl.slideToggle(
			200,
			function() {
				AUI.$(this).css(
					'display',
					AUI.$(this).css('display').replace('none', '')
				);
			}
		);
	}

	/*
		Define max-height of nav so that all overflowing links can be seen and scrolled to
		on a mobile device.
	*/

	function handleNavOverflow() {
		let $navUl = $nav.find('.nav');

		let navOffsetTop = $navUl.offset().top || $nav.outerHeight() + $nav.offset().top;

		let maxNavUlHeight = window.innerHeight - navOffsetTop;

		$navUl.css('max-height', maxNavUlHeight);
	}

	handleNavOverflow();

	function showArticleGroups() {
		for (let i = 0; i < $hiddenArticleGroups.length; i++) {
			let $articleGroup = $hiddenArticleGroups.eq(i);

			let offsetY = Math.min($articleGroup.height(), 100);
			let scrollPositionY = $articleGroup.get(0).getBoundingClientRect().top;

			let hasClassShow = scrollPositionY + offsetY < window.innerHeight;

			if (hasClassShow) {
				$articleGroup.addClass('visible');

				// Remove element from jQuery object to reduce calculations
				$hiddenArticleGroups = $hiddenArticleGroups.not($articleGroup);

				i--;
			}
		}
	}

	showArticleGroups();

	AUI.$(window).resize(
		AUI._.debounce(
			function() {
				let $navDropdownBtns = $nav.find('.nav-btn, span.icon-angle-down');
				let $navDropdownMenus = $nav.find('.nav, .child-menu, .grandchild-menu');

				handleNavOverflow();
				showArticleGroups();

				$navDropdownMenus.css('display', '');
				$navDropdownBtns.removeClass('expand');
			},
			150
		)
	);

	$childDropdownBtns.each(
		function() {
			this.onclick = function() {
				handleDropdownClick(this);
			};
		}
	);

	$navBtnDiv.get(0).onclick = function() {
		handleDropdownClick(this);
	};

	$parentDropdownBtns.each(
		function() {
			this.onclick = function() {
				if (window.innerWidth < 768) {
					handleDropdownClick(this);
				}
			};
		}
	);

	// Add class to make portlet content visible when scrolled to
	AUI.$(window).scroll(
		AUI._.throttle(
			showArticleGroups,
			200
		)
	);
}