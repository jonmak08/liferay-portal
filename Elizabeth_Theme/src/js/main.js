window.addEventListener('scroll', () => {
	if(document.querySelector('header')!=null){
		document.querySelector('header').classList.toggle('scrolled', document.documentElement.scrollTop > 100);
	}
});

const burger = document.querySelector('.burger');
const nav = document.querySelector('.mr-auto');
const navBackground = document.querySelector('.theme-header');
const userIcon = document.querySelector('.user-bar');
const logo = document.querySelector('.burger-logo')

burger.addEventListener('click', () => {
	if(burger != null && nav != null && navBackground != null && userIcon != null && logo != null) {
		nav.classList.toggle('nav-toggler');
		navBackground.classList.toggle('background-toggler');
		userIcon.classList.toggle('burger-inactive');
		logo.classList.toggle('hide-logo');
	}
});