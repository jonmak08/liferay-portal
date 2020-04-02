window.addEventListener('scroll', () => {
	document.querySelector('header').classList.toggle('scrolled', document.documentElement.scrollTop > 150);
});

const burgerNav = () => {
	const burger = document.querySelector('.burger');
	const nav = document.querySelector('.mr-auto');
	const navBackground = document.querySelector('.theme-header');
	const header = document.querySelector('.burger-header');
	const user = document.querySelector('.theme-user')

	burger.addEventListener('click', () => {
		nav.classList.toggle('nav-toggler');
		navBackground.classList.toggle('background-toggler');
		header.classList.toggle('burger-inactive-header');
		user.classList.toggle('inactive-user');
	});
}

burgerNav();