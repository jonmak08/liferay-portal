const app = (() => {
	let body = document.querySelector('body');
	let darkBackgroundElement = document.querySelector(".dark--background");
	let menuHeader = document.querySelector('.menu-title');
	let menuIcon = document.querySelector('.menu-icon');
	let parallax = document.querySelector(".parallax");


	menuHeader.addEventListener('click', () => body.classList.toggle('nav-active'));
	menuIcon.addEventListener('click', () => body.classList.toggle('nav-active'));

	window.addEventListener('scroll', function () {
		debounce(scrollParallax(parallax));

		debounce(changeHamburgerColor(darkBackgroundElement));
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

const isInViewport = element => {
	const bounding = element.getBoundingClientRect();

	if (bounding.top >= 0 || bounding.bottom >= 0) {
		return true;

	} else {
		return false;
	}
};

const scrollParallax = element => {

	if (isInViewport(element)) {
		let offset = window.pageYOffset;

		element.style.backgroundPositionY = offset * .08 + 'rem';
	}
};

const changeHamburgerColor = element => {
	let hamburgerIcon = document.querySelectorAll('.menu-icon--line')

	let hamburgerMenuText = document.querySelector('.menu-title')

	if (!isInViewport(element)) {

		hamburgerMenuText.classList.add('menu-title--dark');

		hamburgerIcon.forEach(line => {
			line.classList.add('menu-icon--line--dark');
		})

	} else {

		hamburgerMenuText.classList.remove('menu-title--dark');

		hamburgerIcon.forEach(line => {
			line.classList.remove('menu-icon--line--dark');
		})
	}
}

