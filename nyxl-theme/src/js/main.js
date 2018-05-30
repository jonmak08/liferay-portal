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
	let helloWorldPortlet = document.querySelector(elem);

	if (helloWorldPortlet) {
		helloWorldPortlet.classList.add('full-width');
	}
	return helloWorldPortlet;
};

addEventListener('load', addFullWidth('#portlet_com_liferay_hello_world_web_portlet_HelloWorldPortlet'));

// for debugging CSS

// [].forEach.call($$("*"),function(a){a.style.outline="1px solid #"+(~~(Math.random()*(1<<24))).toString(16)})

var banner = document.querySelector('#banner');
banner.addEventListener('click', function(event) {
	if (event.target.className === 'icon-caret-down') {
		event.preventDefault();
		console.log('you clicked the carot')
	}

})