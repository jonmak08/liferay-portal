window.addEventListener('scroll', () => {
	document.querySelector('header').classList.toggle('scrolled', document.documentElement.scrollTop>150);
});