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