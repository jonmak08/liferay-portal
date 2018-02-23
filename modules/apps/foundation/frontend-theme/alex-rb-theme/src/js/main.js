// This function gets loaded when everything, including the portlets, is on the page
Liferay.on('allPortletsReady', myPageJS);

function myPageJS() {
	let nav = AUI.$('#navigation');
	let navBtnDiv = nav.find('div.nav-btn');
	let parentDropdownBtn = nav.find('> ul > li > span.icon-angle-down');
	let childDropdownBtns = nav.find('.child-menu span.icon-angle-down');

	navBtnDiv.get(0).onclick = function() {
		let navUl = nav.find('.nav');

		$(this).toggleClass('expand');

		navUl.slideToggle(
			300,
			function() {
				$(this).css('display', $(this).css('display').replace('none', ''));
			}
		);
	};

	function dropdownClickHandler(target) {
		let siblingUl = $(target).find('+ ul');

		$(target).toggleClass('expand');

		siblingUl.slideToggle(
			300,
			function() {
				$(this).css('display', $(this).css('display').replace('none', ''));
			}
		);
	}

	childDropdownBtns.each(
		function() {
			this.onclick = function() {
				dropdownClickHandler(this);
			};
		}
	);

	parentDropdownBtn.get(0).onclick = function() {
		if (window.innerWidth < 768) {
			dropdownClickHandler(this);
		}
	};
}