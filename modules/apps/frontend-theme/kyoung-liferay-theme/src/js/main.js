AUI().ready(
	'liferay-sign-in-modal',
	function(A) {
		var signIn = A.one('.sign-in > a');

		if (signIn && signIn.getData('redirect') !== 'true') {
			signIn.plug(Liferay.SignInModal);
		}
	}
);

YUI().use(
	'aui-tooltip',
	function(Y) {
		new Y.TooltipDelegate(
			{
				position: 'bottom',
				trigger: '#kyTooltip'
			}
		);
	}
);

(function($) {
	$('.menu-btn').click(
		function(event) {
			event.preventDefault();

			$('.menu-full-overlay').addClass('menu-full-overlay-show');
		}
	);

    $('.menu-close-btn').click(
		function(event) {
			event.preventDefault();

			$('.menu-full-overlay').removeClass('menu-full-overlay-show');
		}
	);

	$('.menu-nav a').click(
		function() {
			$('.menu-full-overlay').removeClass('menu-full-overlay-show');
		}
	);

	/* ---------- Video Popup ---------- */

	$('.video-play-btn').click(
		function(event) {
			event.preventDefault();

			$('.video-iframe-area').addClass('video-iframe-display');
		}
	);

	$('.video-close-btn').click(
		function(event) {
			event.preventDefault();

			$('.video-iframe-area').removeClass('video-iframe-display');
		}
	);

	$('.video-close-btn').click(
		function() {
			$('.video-iframe').attr('src', $('.video-iframe').attr('src'));
		}
	);

	$('.video-iframe-area').click(
		function(event) {
			event.preventDefault();

			$('.video-iframe-area').removeClass('video-iframe-display');
		}
	);

	$('.video-iframe-area, .video-iframe').click(
		function(e) {
			e.stopPropagation();
		}
	);

	$('.panel-heading').click(
		function() {
			if ($(this).children().find('.fa').hasClass('fa-minus')) {
				$(this).children().find('.fa').removeClass('fa-minus').addClass('fa-plus');
			}

			else {
				$(this).children().find('.fa').removeClass('fa-plus').addClass('fa-minus');
			}
		}
	);

	/* ---------- jQuery Carousel ---------- */

	$num = $('.my-card').length;
	$even = $num / 2;
	$odd = ($num + 1) / 2;

	if ($num % 2 == 0) {
		$('.my-card:nth-child(' + $even + ')').addClass('active');
		$('.my-card:nth-child(' + $even + ')').prev().addClass('prev');
		$('.my-card:nth-child(' + $even + ')').next().addClass('next');
	}

	else {
		$('.my-card:nth-child(' + $odd + ')').addClass('active');
		$('.my-card:nth-child(' + $odd + ')').prev().addClass('prev');
		$('.my-card:nth-child(' + $odd + ')').next().addClass('next');
	}

	$('.my-card').click(
		function() {
			$slide = $('.active').width();

			if ($(this).hasClass('next')) {
				$('.card-carousel').stop(false, true).animate({left: '-=' + $slide});
			}

			else if ($(this).hasClass('prev')) {
				$('.card-carousel').stop(false, true).animate({left: '+=' + $slide});
			}

			$(this).removeClass('prev next');
			$(this).siblings().removeClass('prev active next');
			$(this).addClass('active');
			$(this).prev().addClass('prev');
			$(this).next().addClass('next');
		}
	);

	/* ---------- Keyboard Nav ---------- */

	$('html body').keydown(
		function(e) {

			if (e.keyCode == 37) {
				$('.active').prev().trigger('click');
			}

			else if (e.keyCode == 39) {
				$('.active').next().trigger('click');
			}
		}
	);

})(jQuery);