const app = (() => {
	let body = document.querySelector('body');
	let menuHeader = document.querySelector('.menu-title');
	let menuIcon = document.querySelector('.menu-icon');
	let parallax = document.querySelector(".parallax");
	
	applyListeners = () => {
		menuHeader.addEventListener('click', () => toggleClass(body, 'nav-active'));
		menuIcon.addEventListener('click', () => toggleClass(body, 'nav-active'));

	}
	
	const debounce = fn => {
		let timeout;
	
		return () => {
			let arguments = arguments;
	
			if (timeout) {
				window.cancelAnimationFrame(timeout);
			}
	
			timeout = window.requestAnimationFrame(function () {
				fn.apply(this, arguments);
			});
		}
	};
	
	const ScrollHeadParallax = () => {
		let offset = window.pageYOffset;

		parallax.style.backgroundPositionY = offset * .08 + 'rem';
	};
		
	window.addEventListener('scroll', function () {

		debounce(ScrollHeadParallax());
	}, false);

})();
