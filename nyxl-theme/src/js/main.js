AUI().ready(
	'liferay-sign-in-modal',
	function(A) {
		var signIn = A.one('.sign-in > a');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	}
);

var addFullWidth = (elem) => {
	let helloWorldPortlet = document.getElementById(elem);

	if (helloWorldPortlet) {
		helloWorldPortlet.classList.add('full-width');
	}
	return helloWorldPortlet;
};

addEventListener('load', addFullWidth('#portlet_com_liferay_hello_world_web_portlet_HelloWorldPortlet'));

var banner = document.getElementById('banner');

if (banner) {
	banner.addEventListener('click', function(event) {
		if (event.target.className === 'icon-caret-down') {
			return event.preventDefault();
		}
	})
}


// only works on first elements from the pictures 
//and has issues when Liferay create <picture> elements

// window.onload = event => {
// var playerCard = document.getElementById('player-card');
// playerCard.addEventListener('mouseenter', function (event) {
// 	var profile = event.target.childNodes[1];
// 	var profileFull = profile.nextElementSibling;
// 	console.log('evt', event.target);
// 	console.log('profile', profile);
// 	console.log('profileFull', profileFull);
// 	profile.classList.toggle('hidden');
// 	profileFull.classList.toggle('hidden');
// });
// playerCard.addEventListener('mouseexit', function (event) {
// 	var profile = event.target.childNodes[1];
// 	var profileFull = profile.nextElementSibling;
// 	console.log('evt', event.target);
// 	console.log('profile', profile);
// 	console.log('profileFull', profileFull);
// 	profile.classList.toggle('hidden');
// 	profileFull.classList.toggle('hidden');
// });

// }

