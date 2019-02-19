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
	var nestedNavigation = document.getElementById('nested-navigation');

	if (nestedNavigation) {
		if (nestedNavigation.style.display === 'none') {
			nestedNavigation.style.display = 'block';
		}
		else {
			nestedNavigation.style.display = 'none';
		}
	}
}

document.getElementById('toggle-navigation').onclick = toggleNav;

(function($) {
	$('#slider-prev').click(
		function() {
			let sliderItemWidth = parseInt($('.slider-item').first().css('width'), 10);
			let marginLeftValue = '0px';

			if (parseInt($('#slider-wrapper').css('marginLeft'), 10) <= -sliderItemWidth) {
				marginLeftValue = '+=' + sliderItemWidth + 'px';
			}

			$('#slider-wrapper').animate(
				{
					marginLeft: marginLeftValue
				},
				'fast'
			);
		}
	);

	$('#slider-next').click(
		function() {
			let sliderItemWidth = parseInt($('.slider-item').first().css('width'), 10);
			let sliderMaxMargin = parseInt($('#slider-wrapper').css('width'), 10) - parseInt($('#slider-inner').css('width'), 10);
			let marginLeftValue = '-' + sliderMaxMargin + 'px';

			if (parseInt($('#slider-wrapper').css('marginLeft'), 10) >= sliderItemWidth - sliderMaxMargin) {
				marginLeftValue = '-=' + sliderItemWidth + 'px';
			}

			$('#slider-wrapper').animate(
				{
					marginLeft: marginLeftValue
				},
				'fast'
			);
		}
	);
})(jQuery);