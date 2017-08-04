AUI().ready(
	'liferay-sign-in-modal',
	function (A) {
		const signIn = A.one('#sign-in');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	}
);

AUI().ready(function () {
	const black = 'rgb(24, 24, 24)';
	const paddingLarge = '20px 42px';
	const paddingSmall = '5px 35px';
	let scrollPosition = 0;
	const transparentBlack = 'rgba(255, 255, 255, .8)';
	const transparentWhite = 'rgba(0, 0, 0, 0.15)';
	const white = '#FFF';

	window.sr = ScrollReveal();
	sr.reveal('.portlet-content');

	const carousels = document.querySelectorAll('.carousel');

	for (let i = 0, len = carousels.length; i < len; i++) {
		let carousel = carousels[i];
		new Flickity(
			carousel,
			{
				autoPlay: 5000,
				contain: true,
				pageDots: false,
				pauseAutoPlayOnHover: false,
				setGallerySize: false,
				resize: false
			}
		);
	}

	const changePadding = (size) => {
		size === 'small' ? AUI.$('#navigation').css({'padding': paddingSmall}) : AUI.$('#navigation').css({'padding': paddingLarge});
	}

	const changeBackground = (color) => {
		AUI.$('.navbar-default').css({'background': color});
	}

	const changeFlagSize = (size) => {
		if (size === 'small') {
			AUI.$('.language-wrapper .triangle').css({'border-width': '0 50px 50px 0'});
			AUI.$('.language-wrapper i').css({'top': '10px', 'right': '-44px'});

		}
		else {
			AUI.$('.language-wrapper .triangle').css({'border-width': '0 70px 70px 0'});
			AUI.$('.language-wrapper i').css({'top': '17px', 'right': '-55px'});
		}
	}

	const changeBorder = (color) => {
		AUI.$('#sign-in').css({'border': '1px solid ' + color});
	}

	AUI.$(window).scroll(() => {

		scrollPosition = AUI.$(window).scrollTop();

		if (scrollPosition > 50) {
			changePadding('small');
			changeBackground('white');
			changeFlagSize('small');
			AUI.$('body').hasClass('day') ? changeBorder(black) : changeBorder(white);
			AUI.$('body').hasClass('day') ? changeBackground(white) : changeBackground(black);

		}
		else {
			changePadding('large');
			changeFlagSize();
			AUI.$('body').hasClass('day') ? changeBorder(white) : changeBorder(black);
			AUI.$('body').hasClass('day') ? changeBackground(transparentBlack) : changeBackground(transparentWhite);
		}
	});

	AUI().use(
		'aui-modal',
		function (Y) {
			var modal = new Y.Modal(
				{
					boundingBox: '#bounding-box',
					centered: true,
					contentBox: '#content-box',
					modal: true,
					resizable: {
						handles: 'b, r'
					},
					visible: false,
					width: 450
				}
			).render();

			Y.one('#showModal').on(
				'click',
				function () {
					AUI.$('#bounding-box .form-group, #content-box').show();
					modal.show();
				}
			);
		}
	);

	AUI.$('#bounding-box span').click(() => {
		AUI.$('#bounding-box .form-group, #content-box').hide();
	});

	const countries = ['ar', 'bg', 'ca', 'cs', 'da', 'de', 'el', 'en', 'es', 'et', 'fa', 'fi', 'fr', 'hi_IN', 'hr', 'hu', 'it', 'ja', 'ko', 'lt', 'nl', 'pl', 'pt_PT', 'ro', 'ru', 'sk', 'sl', 'sr_RS_latin', 'sr_RS', 'sv', 'th', 'tr', 'uk', 'vi', 'zh_CN', 'zh_TW'];

	AUI.$('#save').click(() => {
		const location = window.location;
		const protocol = location.protocol;
		const port = location.port ? ':' + location.port : '';
		const hostname = location.hostname;
		const countryCode = AUI.$('#content-box').find(':selected').val();
		const path = location.pathname;
		const query = location.search;

		const pathArr = path.split('/');
		let newPath = countryCode + path;

		if (pathArr[1] !== undefined) {
			countries.forEach(country => {

				if (country === pathArr[1]) {
					pathArr[1] = countryCode;
					newPath = pathArr.join('/').slice(1);
				}

			})
		}

		const newUrl = protocol + '//' + hostname + port + '/' + newPath + query;
		location.href = newUrl;
	})
})
