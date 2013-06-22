;(function(A, Liferay) {
	A.use(
		'iobase',
		function() {
			Liferay.Language = {
				get: A.cached(
					function(key, extraParams) {
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

						var urlWithAuthToken = url;

						if (Liferay.authToken) {
							urlWithAuthToken += '?p_auth=' + Liferay.authToken;
						}

						A.io(
							urlWithAuthToken,
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

						return value;
					}
				)
			};
		}
	);
})(AUI(), Liferay);