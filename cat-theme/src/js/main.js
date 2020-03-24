const app = (() => {
	let body = document.querySelector('body');
	let menuHeader = document.querySelector('.menu-title');
	let menuIcon = document.querySelector('.menu-icon');
	let parallax = document.querySelector(".parallax");
	
	menuHeader.addEventListener('click', () => body.classList.toggle('nav-active'));
	menuIcon.addEventListener('click', () => body.classList.toggle('nav-active'));
			
	window.addEventListener('scroll', function () {
		debounce(scrollParallax(parallax));
	}, false);

})();

const debounce = fn => {
	let timeout;

	return () => {
		if (timeout) {
			window.cancelAnimationFrame(timeout);
		}

		timeout = window.requestAnimationFrame(function () {
			fn.apply(this, arguments);
		});
	}
};

const isInViewport = (element) => {
	const bounding = element.getBoundingClientRect();
	return (
		bounding.top >= 0 &&
		bounding.left >= 0 &&
		bounding.bottom <= (window.innerHeight || document.documentElement.clientHeight) &&
		bounding.right <= (window.innerWidth || document.documentElement.clientWidth)
	);
};

const scrollParallax = (element) => {
	let offset = window.pageYOffset;
	element.style.backgroundPositionY = offset * .08 + 'rem';
};
