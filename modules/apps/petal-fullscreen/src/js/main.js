$(
	function() {
		var nav = $('.horizontal-nav');
		var WIN = $(window);
		var windowWidth = WIN.width();

		if (windowWidth > 875) {
			var windowFixed = false;

			WIN.scroll(
				function() {
					var preferFixed = WIN.scrollTop() > 0;

					if (preferFixed && !windowFixed) {
						nav.toggleClass('fixed').toggleClass('absolute');

						nav.toggleClass('with-offset').toggleClass('without-offset');

						windowFixed = true;
					}
					else if (!preferFixed && windowFixed) {
						nav.toggleClass('fixed').toggleClass('absolute');

						nav.toggleClass('with-offset').toggleClass('without-offset');

						windowFixed = false;
					}
				}
			);
		}
	}
);
