;(function(A, Liferay) {
	var Language = {};

	Language.get = function(key) {
		return key;
	};

	A.use(
		'io-base',
		function(A) {
			Language.get = A.cached(
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

					var data = {
						p_auth: Liferay.authToken
					};

					A.io(
						url,
						{
							on: {
								complete: function(i, o) {
									value = o.responseText;
								}
							},
							data: data,
							sync: true,
							method: 'POST'
						}
					);

					return value;
				}
			);
		}
	);

	Liferay.Language = Language;
})(AUI(), Liferay);