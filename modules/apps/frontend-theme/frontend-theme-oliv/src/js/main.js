YUI().use(
	'aui-tooltip',
	function(Y) {
		new Y.TooltipDelegate(
			{
				position: 'bottom',
				trigger: '#menu-social li'
			}
		);
	}
);

function toggleNav() {
	if (document.getElementById('nested-navigation').style.display === 'none') {
		document.getElementById('nested-navigation').style.display = 'block';
	}
	else {
		document.getElementById('nested-navigation').style.display = 'none';
	}
}

document.getElementById('toggle-navigation').onclick = toggleNav;

(function($) {
	$('#slider-prev').click(
		function() {
			let sliderItemWidth = parseInt($('.slider-item').first().css('width'), 10);

			if (parseInt($('#slider-wrapper').css('marginLeft'), 10) + sliderItemWidth > 0) {
				$('#slider-wrapper').animate(
					{marginLeft: 0},
					'fast'
				);
			}
			else if (parseInt($('#slider-wrapper').css('marginLeft'), 10) < 0) {
				$('#slider-wrapper').animate(
					{marginLeft: '+=' + sliderItemWidth + 'px'},
					'fast'
				);
			}
		}
	);

	$('#slider-next').click(
		function() {
			let sliderItemWidth = parseInt($('.slider-item').first().css('width'), 10);
			let sliderMaxMargin = parseInt($('#slider-wrapper').css('width'), 10) - parseInt($('.slider-inner').css('width'), 10);

			if (parseInt($('#slider-wrapper').css('marginLeft'), 10) - sliderItemWidth < -sliderMaxMargin) {
				$('#slider-wrapper').animate(
					{marginLeft: '-' + sliderMaxMargin + 'px'},
					'fast'
				);
			}
			else if (parseInt($('#slider-wrapper').css('marginLeft'), 10) > -sliderMaxMargin) {
				$('#slider-wrapper').animate(
					{marginLeft: '-=' + sliderItemWidth + 'px'},
					'fast'
				);
			}
		}
	);
})(jQuery);