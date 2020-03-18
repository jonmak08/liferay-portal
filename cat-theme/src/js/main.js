const app = (() => {
	let body;
	let menu_icon;
	let menu_header;
	let parallax;
	
	const init = () => {
		body = document.querySelector('body');
		menu_icon = document.querySelector('.menu-icon');
		menu_header = document.querySelector('.menu-title');
		menuItems = document.querySelectorAll('.nav__list-item');
		parallax = document.querySelector(".parallax");

		applyListeners();
	}
	
	const applyListeners = () => {
		menu_icon.addEventListener('click', () => toggleClass(body, 'nav-active'));
		menu_header.addEventListener('click', () => toggleClass(body, 'nav-active'));

		window.addEventListener('scroll', () => {
			let offset = window.pageYOffset;

			parallax.style.backgroundPositionY = offset * .08 + 'rem';
		})
	}
	
	const toggleClass = (element, navClass) => {
		if(element.classList.contains(navClass))
			element.classList.remove(navClass);
		else
			element.classList.add(navClass);
	}
	
	init();
})();

