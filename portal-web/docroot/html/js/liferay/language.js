;(function(A, Liferay) {
	A.use(
		'cache',
		'iobase',
		function() {
			Liferay.Language = {
				get: function(key, extraParams) {
					var instance = this;

					var url = themeDisplay.getPathContext() + '/language/' + themeDisplay.getLanguageId() + '/' + key + '/';

					if (extraParams) {
						if (typeof extraParams == 'string') {
							url += extraParams;
						}
						else if (Liferay.Util.isArray(extraParams)) {
							url += extraParams.join('/');
						}
					}

					var value = instance._cache.retrieve(url);

					if (!value) {
						A.io(
							url,
							{
								on: {
									complete: function(i, o) {
										value = o.responseText;
									}
								},
								sync: true,
								type: 'GET'
							}
						);

						instance._cache.add(url, value);
					}

					return value;
				},

				_cache: new A.Cache(
					{
						max: 250,
						on: {
							complete: function(i, o) {
								value = o.responseText;
							}
						},
						sync: true,
						type: 'GET'
					}
				)
			};
		}
	);
})(AUI(), Liferay);