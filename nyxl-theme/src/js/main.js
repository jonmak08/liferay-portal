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

// document.getElementById("portlet_com_liferay_asset_publisher_web_portlet_AssetPublisherPortlet_INSTANCE_HcUvPg6i4Toj").addEventListener('click', function(event) {
// 	var parent = event.target.parentElement;

// 	if (event.target.className === 'profile-full') {
// 		 if (parent.nodeName === 'PICTURE') {
// 			 parent = parent.parentElement;
// 		 }
// 		event.target.classList.toggle('hidden');
// 		parent.firstElementChild.classList.toggle('hidden');
// 	}

// 	if (event.target.className === 'profile') {
// 		event.target.classList.toggle('hidden');
// 		if (parent.lastElementChild.nodeName === 'PICTURE') {
// 			return parent.lastElementChild.childNodes[1].classList.toggle('hidden')
// 		}
// 		parent.lastElementChild.classList.toggle('hidden');
// 	}
// })

/* ---------- Doesn't toggle pictures properly ----------

var toggleProfile = function(event, parent, toggleClass) {
	if (parent.nodeName === 'PICTURE') {
		parent = parent.parentElement;
		if(parent.lastElementChild.childNodes[1]) {
			var imgPath = parent.lastElementChild.childNodes[1];
		}
	}

	if (event.target.className === 'profile-full') {
		parent.firstElementChild.classList.toggle(toggleClass)
		event.target.classList.toggle(toggleClass);
	}
	if (event.target.className === 'profile') {
		if(imgPath) {
			imgPath.classList.toggle(toggleClass)
		}
		else {
			parent.lastElementChild.classList.toggle(toggleClass);
		}
		event.target.classList.toggle(toggleClass);
	}
};

toggleProfile(event, parent, "hidden");
---------- */