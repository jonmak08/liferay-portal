
// const hero_parallax = document.querySelector('#hero');

// window.addEventListener('scroll', function() {
//     const scroll = hero_parallax.scrollTop;

//     console.log(scroll);
//     hero_parallax.style.backgroundSize = `${(100 + scroll/5)  + "%"}`
//     hero_parallax.style.top =  `${-(scroll/10)  + "%"}`
// });


const app = (() => {
	let body;
	let menu_icon;
	let menu_header;
	
	const init = () => {
		body = document.querySelector('body');
		menu_icon = document.querySelector('.menu-icon');
		menu_header = document.querySelector('.menu-title');

		menuItems = document.querySelectorAll('.nav__list-item');

		applyListeners();
	}
	
	const applyListeners = () => {
		menu_icon.addEventListener('click', () => toggleClass(body, 'nav-active'));
		menu_header.addEventListener('click', () => toggleClass(body, 'nav-active'));

	}
	
	const toggleClass = (element, stringClass) => {
		if(element.classList.contains(stringClass))
			element.classList.remove(stringClass);
		else
			element.classList.add(stringClass);
	}
	
	init();
})();

