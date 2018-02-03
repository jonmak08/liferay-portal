Liferay.Loader.require.apply(
	Liferay.Loader,
	$MODULES.concat(
		[
			function(Component) {
				Liferay.component('$ID', new Component.default($CONTEXT, '#$ID'));

				Liferay.once(
					'beforeScreenFlip',
					function() {
						Liferay.component('$ID').dispose();
						Liferay.component('$ID', null);
					}
				);
<<<<<<< HEAD
			},
			function(error) {
				console.error('Unable to load ' + $MODULES);

				Liferay.fire(
					'soyComponentLoadingError',
					{
						error: error,
						modules: $MODULES
					}
				);
=======
>>>>>>> compatible
			}
		]
	)
);